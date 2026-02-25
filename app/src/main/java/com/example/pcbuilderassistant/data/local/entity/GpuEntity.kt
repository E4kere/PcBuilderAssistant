package com.example.pcbuilderassistant.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gpus")
data class GpuEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val vram: Int,
    val powerConsumption: Int,
    val price: Int
)