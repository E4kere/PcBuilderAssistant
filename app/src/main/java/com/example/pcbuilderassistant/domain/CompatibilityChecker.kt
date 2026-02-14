package com.example.pcbuilderassistant.domain

object CompatibilityChecker {

    fun isCpuCompatible(cpu: Cpu, motherboard: Motherboard): Boolean {
        return cpu.socket == motherboard.socket
    }
}
