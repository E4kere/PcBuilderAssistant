package com.example.pcbuilderassistant.domain

import com.example.pcbuilderassistant.data.repository.HardwareRepository
import com.example.pcbuilderassistant.data.local.entity.CpuEntity
import com.example.pcbuilderassistant.data.local.entity.GpuEntity

class BuildGenerator(
    private val repository: HardwareRepository
) {

    suspend fun generate(preferences: UserPreferences): Build {

        val cpus = repository.getAllCpus()
        val gpus = repository.getAllGpus()

        val cpu: CpuEntity
        val gpu: GpuEntity

        when (preferences.purpose) {

            Purpose.GAMING -> {
                cpu = cpus
                    .filter { it.price <= preferences.budget / 2 }
                    .maxByOrNull { it.cores }
                    ?: throw Exception("No CPU found")

                val remaining = preferences.budget - cpu.price

                gpu = gpus
                    .filter { it.price <= remaining }
                    .maxByOrNull { it.vram }
                    ?: throw Exception("No GPU found")
            }

            Purpose.WORK -> {
                cpu = cpus
                    .filter { it.price <= preferences.budget * 0.7 }
                    .maxByOrNull { it.cores }
                    ?: throw Exception("No CPU found")

                val remaining = preferences.budget - cpu.price

                gpu = gpus
                    .filter { it.price <= remaining }
                    .maxByOrNull { it.vram }
                    ?: throw Exception("No GPU found")
            }

            Purpose.MODELING -> {
                cpu = cpus
                    .filter { it.price <= preferences.budget * 0.6 }
                    .maxByOrNull { it.cores }
                    ?: throw Exception("No CPU found")

        val remainingBudget = preferences.budget - cpu.price

        val gpu = gpus
            .filter { it.price <= remainingBudget }
            .maxByOrNull { it.vram }
            ?: throw Exception("No suitable GPU found")

        val totalPrice = cpu.price + gpu.price

        return Build(
            cpu = cpu,
            gpu = gpu,
            totalPrice = totalPrice,
            explanation = "CPU выбран по количеству ядер, GPU по объёму VRAM в рамках бюджета."
        )
    }
}