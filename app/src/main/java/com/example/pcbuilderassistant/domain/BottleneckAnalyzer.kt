package com.example.pcbuilderassistant.domain

object BottleneckAnalyzer {

    fun analyze(cpu: Cpu, gpu: Gpu): String {

        val ratio = gpu.tdp.toDouble() / cpu.tdp.toDouble()

        return when {
            ratio > 3.0 ->
                "CPU bottleneck: процессор ограничивает производительность видеокарты"

            ratio < 1.2 ->
                "GPU bottleneck: видеокарта слишком слабая для данного процессора"

            else ->
                "Система сбалансирована"
        }
    }
}
