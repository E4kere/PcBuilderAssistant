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

        val cpu = cpus
            .filter { it.price <= preferences.budget }
            .maxByOrNull { it.cores }
            ?: throw Exception("No suitable CPU found")

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