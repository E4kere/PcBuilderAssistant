package com.example.pcbuilderassistant.domain

import com.example.pcbuilderassistant.data.local.entity.CpuEntity
import com.example.pcbuilderassistant.data.local.entity.GpuEntity
import com.example.pcbuilderassistant.data.local.entity.MotherboardEntity
import com.example.pcbuilderassistant.data.repository.HardwareRepository

class BuildGenerator(
    private val repository: HardwareRepository
) {

    private val scoringEngine = ScoringEngine()

    suspend fun generate(preferences: UserPreferences): Build? {

        val cpus = repository.getAllCpus()
        val gpus = repository.getAllGpus()

        if (cpus.isEmpty() || gpus.isEmpty()) return null

        var bestScore = Double.MIN_VALUE
        var bestCpu: CpuEntity? = null
        var bestGpu: GpuEntity? = null
        var bestMotherboard: MotherboardEntity? = null

        for (cpu in cpus) {


            val compatibleMotherboards =
                repository.getMotherboardsBySocket(cpu.socket)

            if (compatibleMotherboards.isEmpty()) continue

            for (gpu in gpus) {

                for (motherboard in compatibleMotherboards) {

                    val totalPrice =
                        cpu.price + gpu.price + motherboard.price

                    // Проверка бюджета
                    if (totalPrice > preferences.budget) continue

                    val score = scoringEngine.scoreBuild(
                        cpu = cpu,
                        gpu = gpu,
                        motherboard = motherboard,
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
        }

        if (bestCpu == null || bestGpu == null || bestMotherboard == null)
            return null

        return Build(
            cpu = bestCpu,
            gpu = bestGpu,
            motherboard = bestMotherboard,
            totalPrice = bestCpu.price + bestGpu.price + bestMotherboard.price,
            score = bestScore
        )
    }
}