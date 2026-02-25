package com.example.pcbuilderassistant.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pcbuilderassistant.data.local.dao.CpuDao
import com.example.pcbuilderassistant.data.local.entity.CpuEntity
import com.example.pcbuilderassistant.data.local.dao.GpuDao
import com.example.pcbuilderassistant.data.local.entity.GpuEntity


@Database(
    entities = [CpuEntity::class, GpuEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cpuDao(): CpuDao
    abstract fun gpuDao(): GpuDao
}