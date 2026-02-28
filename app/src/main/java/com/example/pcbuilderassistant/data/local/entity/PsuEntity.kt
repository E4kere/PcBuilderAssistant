package com.example.pcbuilderassistant.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "psu")
data class PsuEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val wattage: Int,
    val efficiency: String,
    val price: Int
)