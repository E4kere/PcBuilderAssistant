package com.example.pcbuilderassistant.domain

import com.example.pcbuilderassistant.data.local.entity.CpuEntity
import com.example.pcbuilderassistant.data.local.entity.GpuEntity
import com.example.pcbuilderassistant.data.repository.HardwareRepository
import com.example.pcbuilderassistant.data.local.dao.MotherboardDao
import com.example.pcbuilderassistant.data.local.entity.MotherboardEntity


class BuildGenerator(
    private val repository: HardwareRepository
) {

    private val scoringEngine = ScoringEngine()

    suspend fun generate(preferences: UserPreferences): Build? {

        val cpus = repository.getAllCpus()
        val gpus = repository.getAllGpus()
        val compatibleMotherboards =
            repository.getMotherboardsBySocket(cpu.socket)

        if (cpus.isEmpty() || gpus.isEmpty() || motherboards.isEmpty())
            return null

        var bestScore = Double.MIN_VALUE
        var bestCpu: CpuEntity? = null
        var bestGpu: GpuEntity? = null
        var bestMotherboard: MotherboardEntity? = null

        for (cpu in cpus) {
            for (gpu in gpus) {

                val compatibleMotherboards =
                    motherboards.filter { it.socket == cpu.socket }

                if (compatibleMotherboards.isEmpty()) continue

                val motherboard =
                    compatibleMotherboards.minByOrNull { it.price } ?: continue

                val totalPrice =
                    cpu.price + gpu.price + motherboard.price

                if (totalPrice > preferences.budget) continue

                val score = scoringEngine.scoreBuild(
                    cpu = cpu,
                    gpu = gpu,
                    budget = preferences.budget,
                    purpose = preferences.purpose,
                    priority = preferences.priority
                )

                if (score > bestScore) {
                    bestScore = score
                    bestCpu = cpu
                    bestGpu = gpu
                    bestMotherboard = motherboard
                }
            }
        }

        if (bestCpu == null || bestGpu == null || bestMotherboard == null)
            return null

        val total =
            bestCpu.price + bestGpu.price + bestMotherboard.price

        return Build(
            cpu = bestCpu,
            gpu = bestGpu,
            motherboard = bestMotherboard,
            totalPrice = total,
            explanation = "Сборка подобрана с учётом совместимости сокета"
        )
    }
}