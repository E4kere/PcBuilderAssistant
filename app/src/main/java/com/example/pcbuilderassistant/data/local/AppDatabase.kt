package com.example.pcbuilderassistant.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pcbuilderassistant.data.local.dao.CpuDao
import com.example.pcbuilderassistant.data.local.dao.GpuDao
import com.example.pcbuilderassistant.data.local.dao.MotherboardDao
import com.example.pcbuilderassistant.data.local.entity.CpuEntity
import com.example.pcbuilderassistant.data.local.entity.GpuEntity
import com.example.pcbuilderassistant.data.local.entity.MotherboardEntity

@Database(
    entities = [
        CpuEntity::class,
        GpuEntity::class,
        MotherboardEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cpuDao(): CpuDao
    abstract fun gpuDao(): GpuDao
    abstract fun motherboardDao(): MotherboardDao
}