package com.example.pcbuilderassistant.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "motherboards")
data class MotherboardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val socket: String,
    val chipset: String,

    val formFactor: String,      // ATX, mATX, ITX
    val ramType: String,         // DDR4, DDR5
    val maxRamFrequency: Int,
    val ramSlots: Int,

    val pcieVersion: Int,        // 3, 4, 5
    val hasWifi: Boolean,

    val price: Int
)