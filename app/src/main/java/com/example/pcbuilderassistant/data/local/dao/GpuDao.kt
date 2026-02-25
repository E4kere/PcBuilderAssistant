package com.example.pcbuilderassistant.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pcbuilderassistant.data.local.entity.GpuEntity

@Dao
interface GpuDao {

    @Query("SELECT * FROM gpus")
    suspend fun getAll(): List<GpuEntity>

    @Insert
    suspend fun insertAll(gpus: List<GpuEntity>)
}