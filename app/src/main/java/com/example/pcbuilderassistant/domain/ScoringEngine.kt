package com.example.pcbuilderassistant.domain

import com.example.pcbuilderassistant.data.local.entity.CpuEntity
import com.example.pcbuilderassistant.data.local.entity.GpuEntity
import com.example.pcbuilderassistant.data.local.entity.MotherboardEntity

class ScoringEngine {

    fun scoreBuild(
        cpu: CpuEntity,
        gpu: GpuEntity,
        motherboard: MotherboardEntity,
        budget: Int,
        purpose: Purpose,
        priority: Priority
    ): Double {

        val cpuPerformance = cpu.cores * 2 + cpu.threads
        val gpuPerformance = gpu.vram * 10

        val totalPrice = cpu.price + gpu.price + motherboard.price
        val budgetUsage = totalPrice.toDouble() / budget

        val (cpuWeight, gpuWeight) = when (purpose) {
            Purpose.GAMING -> 0.4 to 0.6
            Purpose.WORK -> 0.7 to 0.3
            Purpose.MODELING -> 0.5 to 0.5
        }

        val platformScore = when (motherboard.chipset) {
            "Z790", "X670E" -> 20
            "B650", "B760" -> 10
            else -> 5
        }

        val performanceScore =
            cpuPerformance * cpuWeight +
                    gpuPerformance * gpuWeight +
                    platformScore

        return when (priority) {
            Priority.CHEAP -> performanceScore / totalPrice
            Priority.PERFORMANCE -> performanceScore
            Priority.BALANCED -> performanceScore * 0.7 + budgetUsage * 100 * 0.3
        }
    }
}