package com.example.pcbuilderassistant.domain

import com.example.pcbuilderassistant.data.local.entity.*

class AdvancedScoringEngine {

    fun calculateScore(
        cpu: CpuEntity,
        gpu: GpuEntity,
        motherboard: MotherboardEntity,
        psu: PsuEntity,
        fps: Int,
        budget: Int
    ): Double {

        val weights = mapOf(
            "cpu" to 0.25,
            "gpu" to 0.35,
            "motherboard" to 0.10,
            "psu" to 0.10,
            "fps" to 0.20
        )

        val cpuScore = cpu.cores * 10
        val gpuScore = gpu.vram * 15
        val mbScore = motherboard.pcieVersion * 5
        val psuScore = psu.wattage / 10
        val fpsScore = fps.toDouble()

        return cpuScore * weights["cpu"]!! +
                gpuScore * weights["gpu"]!! +
                mbScore * weights["motherboard"]!! +
                psuScore * weights["psu"]!! +
                fpsScore * weights["fps"]!!
    }
}