package com.example.pcbuilderassistant.domain

import com.example.pcbuilderassistant.data.local.entity.*

data class Build(
    val cpu: CpuEntity,
    val gpu: GpuEntity,
    val motherboard: MotherboardEntity,
    val psu: PsuEntity,
    val totalPrice: Int,
    val score: Double,
    val estimatedFps: Int,
    val explanation: String

)