package com.example.pcbuilderassistant.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pcbuilderassistant.data.local.entity.MotherboardEntity

@Dao
interface MotherboardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(motherboards: List<MotherboardEntity>)

    @Query("SELECT * FROM motherboards")
    suspend fun getAll(): List<MotherboardEntity>

    @Query("""
        SELECT * FROM motherboards
        WHERE socket = :socket
    """)
    suspend fun getBySocket(socket: String): List<MotherboardEntity>
}