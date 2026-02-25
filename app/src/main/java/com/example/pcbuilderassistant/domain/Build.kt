package com.example.pcbuilderassistant.domain

import com.example.pcbuilderassistant.data.local.entity.CpuEntity
import com.example.pcbuilderassistant.data.local.entity.GpuEntity

data class Build(
    val cpu: CpuEntity,
    val gpu: GpuEntity,
    val totalPrice: Int,
    val explanation: String
)