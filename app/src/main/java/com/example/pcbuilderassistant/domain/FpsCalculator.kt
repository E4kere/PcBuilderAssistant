package com.example.pcbuilderassistant.domain

import com.example.pcbuilderassistant.data.local.entity.CpuEntity
import com.example.pcbuilderassistant.data.local.entity.GpuEntity

class FpsCalculator {

    fun estimateFps(
        cpu: CpuEntity,
        gpu: GpuEntity,
        resolution: Resolution
    ): Int {

        val gpuBase = gpu.vram * 25
        val cpuFactor = cpu.cores * 5

        val baseFps = gpuBase + cpuFactor

        return when (resolution) {
            Resolution.FULL_HD -> baseFps
            Resolution.QHD -> (baseFps * 0.75).toInt()
            Resolution.UHD -> (baseFps * 0.55).toInt()
        }
    }
}