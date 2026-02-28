package com.example.pcbuilderassistant.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pcbuilderassistant.data.local.entity.PsuEntity

@Dao
interface PsuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(psus: List<PsuEntity>)

    @Query("SELECT * FROM psu")
    suspend fun getAll(): List<PsuEntity>
}