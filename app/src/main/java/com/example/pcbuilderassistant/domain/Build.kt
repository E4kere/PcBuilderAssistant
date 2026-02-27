package com.example.pcbuilderassistant.domain

import com.example.pcbuilderassistant.data.local.entity.CpuEntity
import com.example.pcbuilderassistant.data.local.entity.GpuEntity
import com.example.pcbuilderassistant.data.local.dao.MotherboardDao
import com.example.pcbuilderassistant.data.local.entity.MotherboardEntity

data class Build(
    val cpu: CpuEntity,
    val gpu: GpuEntity,
    val motherboard: MotherboardEntity,
    val totalPrice: Int,
    val score: Double
)