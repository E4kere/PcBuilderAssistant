package com.example.pcbuilderassistant.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pcbuilderassistant.data.local.dao.*
import com.example.pcbuilderassistant.data.local.entity.*

@Database(
    entities = [
        CpuEntity::class,
        GpuEntity::class,
        MotherboardEntity::class,
        PsuEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cpuDao(): CpuDao
    abstract fun gpuDao(): GpuDao
    abstract fun motherboardDao(): MotherboardDao
    abstract fun psuDao(): PsuDao
}