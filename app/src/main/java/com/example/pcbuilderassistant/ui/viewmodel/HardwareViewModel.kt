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
class HardwareViewModel(application: Application) : AndroidViewModel(application) {

    private val database = DatabaseProvider.getDatabase(application)

    private val repository = HardwareRepository(
        database.cpuDao(),
        database.gpuDao()
    )
    private val buildGenerator = BuildGenerator(repository)

    private val _cpus = MutableStateFlow<List<CpuEntity>>(emptyList())
    val cpus: StateFlow<List<CpuEntity>> = _cpus

    private val _generatedCpu = MutableStateFlow("")
    val generatedCpu: StateFlow<String> = _generatedCpu

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {

            if (repository.getAllCpus().isEmpty()) {
                repository.insertCpus(
                    listOf(
                        CpuEntity(
                            name = "Ryzen 5 5600X",
                            cores = 6,
                            threads = 12,
                            baseClock = 3.7,
                            socket = "AM4",
                            tdp = 65,
                            price = 85000
                        ),
                        CpuEntity(
                            name = "Intel i7-12700K",
                            cores = 12,
                            threads = 20,
                            baseClock = 3.6,
                            socket = "LGA1700",
                            tdp = 125,
                            price = 160000
                        )
                    )
                )
            }


            _cpus.value = repository.getAllCpus()
            if (repository.getAllGpus().isEmpty()) {
                repository.insertGpus(
                    listOf(
                        GpuEntity(
                            name = "RTX 3060",
                            vram = 12,
                            powerConsumption = 170,
                            price = 140000
                        ),
                        GpuEntity(
                            name = "RX 6600",
                            vram = 8,
                            powerConsumption = 132,
                            price = 120000
                        )
                    )
                )
            }
        }

    }

    fun generateBuild(preferences: UserPreferences) {
        viewModelScope.launch(Dispatchers.IO) {

            val build = buildGenerator.generate(preferences)

            _generatedCpu.value =
                """
            CPU: ${build.cpu.name}
            GPU: ${build.gpu.name}
            
            Total price: ${build.totalPrice} ₸
            
            ${build.explanation}
            """.trimIndent()
        }
    }
}