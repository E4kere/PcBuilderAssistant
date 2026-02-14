package com.example.pcbuilderassistant.domain

import com.example.pcbuilderassistant.data.FakeData

object BuildGenerator {

    fun generate(pref: UserPreferences): Build {

        // ---- GPU budget distribution ----
        val gpuBudget = when (pref.purpose) {
            Purpose.GAMING -> (pref.budget * 0.45).toInt()
            Purpose.WORK -> (pref.budget * 0.25).toInt()
            Purpose.MODELING -> (pref.budget * 0.35).toInt()
        }

        val gpu = FakeData.gpuList
            .filter { it.price <= gpuBudget }
            .maxByOrNull { it.price }
            ?: FakeData.gpuList.first()

        // ---- CPU budget distribution ----
        val cpuBudget = (pref.budget * 0.25).toInt()

        val cpu = FakeData.cpuList
            .filter { it.price <= cpuBudget }
            .maxByOrNull { it.price }
            ?: FakeData.cpuList.first()

        // ---- Motherboard compatibility ----
        val motherboard = FakeData.motherboardList
            .first { it.socket == cpu.socket }

        // ---- PSU calculation ----
        val requiredPower = PowerCalculator.calculateRequiredPower(cpu, gpu)

        val psu = FakeData.psuList
            .first { it.wattage >= requiredPower }

        // ---- Explanation (expert reasoning) ----
        val explanation = when (pref.purpose) {
            Purpose.GAMING ->
                "Для игр основной упор делается на видеокарту, поэтому большая часть бюджета выделена GPU."

            Purpose.WORK ->
                "Для рабочих задач важна вычислительная мощность процессора, поэтому выбран более производительный CPU."

            Purpose.MODELING ->
                "3D-моделирование требует баланс CPU и GPU, поэтому подобрана сбалансированная конфигурация."
        }
        val balance = BottleneckAnalyzer.analyze(cpu, gpu)

        return Build(cpu, gpu, motherboard, psu, explanation, balance)

    }
}
