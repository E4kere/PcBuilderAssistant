package com.example.pcbuilderassistant.data.local.model

import com.example.pcbuilderassistant.data.local.entity.*

data class HardwareJson(
    val cpus: List<CpuEntity>,
    val gpus: List<GpuEntity>,
    val motherboards: List<MotherboardEntity>,
    val psus: List<PsuEntity>
)