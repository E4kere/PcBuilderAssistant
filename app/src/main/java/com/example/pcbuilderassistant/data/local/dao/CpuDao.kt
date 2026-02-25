package com.example.pcbuilderassistant.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pcbuilderassistant.data.local.entity.CpuEntity

@Dao
interface CpuDao {

    @Insert
    suspend fun insertAll(cpus: List<CpuEntity>)

    @Query("SELECT * FROM cpus")
    suspend fun getAll(): List<CpuEntity>
}