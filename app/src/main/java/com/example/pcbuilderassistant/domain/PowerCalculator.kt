package com.example.pcbuilderassistant.domain

object PowerCalculator {

    fun calculateRequiredPower(cpu: Cpu, gpu: Gpu): Int {
        // +100 запас на остальные компоненты
        return cpu.tdp + gpu.tdp + 100

    }

    fun isEnough(psu: Psu, requiredPower: Int): Boolean {
        return psu.wattage >= requiredPower
    }
}
