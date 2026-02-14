package com.example.pcbuilderassistant.domain

data class Build(
    val cpu: Cpu,
    val gpu: Gpu,
    val motherboard: Motherboard,
    val psu: Psu,
    val explanation: String,
    val balance: String
)

