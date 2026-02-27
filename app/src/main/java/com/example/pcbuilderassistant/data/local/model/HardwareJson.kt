package com.example.pcbuilderassistant.data.local.model

import com.example.pcbuilderassistant.data.local.entity.CpuEntity
import com.example.pcbuilderassistant.data.local.entity.GpuEntity
import com.example.pcbuilderassistant.data.local.dao.MotherboardDao
import com.example.pcbuilderassistant.data.local.entity.MotherboardEntity


data class HardwareJson(
    val cpus: List<CpuEntity>,
    val gpus: List<GpuEntity>,
    val motherboards: List<MotherboardEntity>
)