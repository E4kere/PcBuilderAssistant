package com.example.pcbuilderassistant.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcbuilderassistant.data.local.DatabaseProvider
import com.example.pcbuilderassistant.data.local.entity.CpuEntity
import com.example.pcbuilderassistant.data.repository.HardwareRepository
import com.example.pcbuilderassistant.domain.BuildGenerator
import com.example.pcbuilderassistant.domain.UserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.pcbuilderassistant.data.local.dao.GpuDao
import com.example.pcbuilderassistant.data.local.entity.GpuEntity
import com.example.pcbuilderassistant.data.local.JsonLoader
class HardwareViewModel(application: Application) : AndroidViewModel(application) {

    private val database = DatabaseProvider.getDatabase(application)

    private val repository = HardwareRepository(
        database.cpuDao(),
        database.gpuDao(),
        database.motherboardDao(),
        database.psuDao()
    )
    private val buildGenerator = BuildGenerator(repository)

    private val _cpus = MutableStateFlow<List<CpuEntity>>(emptyList())
    val cpus: StateFlow<List<CpuEntity>> = _cpus

    private val _generatedCpu = MutableStateFlow("")
    val generatedCpu: StateFlow<String> = _generatedCpu

    fun loadData() {
        val jsonData = JsonLoader.loadHardware(getApplication())

        println("CPUs from JSON: ${jsonData.cpus.size}")
        println("GPUs from JSON: ${jsonData.gpus.size}")
        viewModelScope.launch(Dispatchers.IO) {

            if (repository.getAllCpus().isEmpty()) {

                val jsonData = JsonLoader.loadHardware(getApplication())

                repository.insertCpus(jsonData.cpus)
                repository.insertGpus(jsonData.gpus)
                repository.insertMotherboards(jsonData.motherboards)
                repository.insertPsus(jsonData.psus)
            }

            _cpus.value = repository.getAllCpus()
        }
    }
    fun generateBuild(preferences: UserPreferences) {
        viewModelScope.launch(Dispatchers.IO) {

            val build = buildGenerator.generate(preferences)

            if (build == null) {
                _generatedCpu.value =
                    "Недостаточный бюджет для выбранных параметров"
                return@launch
            }

            _generatedCpu.value =
                """
🎮 РЕКОМЕНДУЕМАЯ СБОРКА

CPU: ${build.cpu.name}
GPU: ${build.gpu.name}
Motherboard: ${build.motherboard.name}
PSU: ${build.psu.name}

Estimated FPS: ${build.estimatedFps}

Total price: ${build.totalPrice} ₸

AI Score: ${"%.2f".format(build.score)}

${build.explanation}
""".trimIndent()
        }
    }

}




