package com.example.pcbuilderassistant.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cpus")
data class CpuEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val cores: Int,
    val threads: Int,
    val baseClock: Double,
    val socket: String,
    val tdp: Int,
    val price: Int
)