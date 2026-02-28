package com.example.pcbuilderassistant.domain

import com.example.pcbuilderassistant.data.local.entity.CpuEntity
import com.example.pcbuilderassistant.data.local.entity.GpuEntity
import com.example.pcbuilderassistant.data.local.entity.MotherboardEntity
import com.example.pcbuilderassistant.data.local.entity.PsuEntity
import com.example.pcbuilderassistant.data.repository.HardwareRepository

class BuildGenerator(
    private val repository: HardwareRepository
) {

    private val scoringEngine = ScoringEngine()
    private val fpsCalculator = FpsCalculator()
    private val advancedScoring = AdvancedScoringEngine()

    suspend fun generate(preferences: UserPreferences): Build? {

        val cpus = repository.getAllCpus()
        val gpus = repository.getAllGpus()
        val psus = repository.getAllPsus()

        var bestBuild: Build? = null
        var bestScore = Double.MIN_VALUE

        for (cpu in cpus) {

            val motherboards =
                repository.getMotherboardsBySocket(cpu.socket)

            if (motherboards.isEmpty()) continue

            for (gpu in gpus) {

                val totalPower = cpu.tdp + gpu.powerConsumption + 100

                for (psu in psus) {

                    // Проверка запаса мощности (20%)
                    if (psu.wattage < totalPower * 1.2) continue

                    // Upgrade режим
                    if (preferences.upgradeMode == UpgradeMode.GPU_FUTURE) {
                        if (psu.wattage < 750) continue
                    }

                    for (motherboard in motherboards) {

                        val totalPrice =
                            cpu.price +
                                    gpu.price +
                                    motherboard.price +
                                    psu.price

                        if (totalPrice > preferences.budget) continue

                        // FPS расчет
                        val estimatedFps = fpsCalculator.estimateFps(
                            cpu,
                            gpu,
                            preferences.resolution
                        )

                        // AI weighted scoring
                        val score = advancedScoring.calculateScore(
                            cpu,
                            gpu,
                            motherboard,
                            psu,
                            estimatedFps,
                            preferences.budget
                        )

                        if (score > bestScore) {

                            bestScore = score

                            bestBuild = Build(
                                cpu = cpu,
                                gpu = gpu,
                                motherboard = motherboard,
                                psu = psu,
                                totalPrice = totalPrice,
                                score = score,
                                estimatedFps = estimatedFps,
                                explanation = generateExplanation(
                                    cpu,
                                    gpu,
                                    motherboard,
                                    psu,
                                    totalPower,
                                    estimatedFps,
                                    score,
                                    preferences
                                )
                            )
                        }
                    }
                }
            }
        }

        return bestBuild
    }

    private fun generateExplanation(
        cpu: CpuEntity,
        gpu: GpuEntity,
        motherboard: MotherboardEntity,
        psu: PsuEntity,
        power: Int,
        fps: Int,
        score: Double,
        preferences: UserPreferences
    ): String {

        return """
Сборка оптимизирована для цели: ${preferences.purpose}

Процессор: ${cpu.name} (${cpu.cores} ядер)
Видеокарта: ${gpu.name} (${gpu.vram} ГБ VRAM)

Разрешение: ${preferences.resolution}
Оценка FPS: ~${fps} кадров/с

Материнская плата: ${motherboard.name}
Сокет: ${motherboard.socket}

Энергопотребление ≈ ${power}W
Блок питания: ${psu.name} (${psu.wattage}W)

Режим апгрейда: ${preferences.upgradeMode}

Итоговый AI-рейтинг: ${"%.2f".format(score)}
""".trimIndent()
    }
}