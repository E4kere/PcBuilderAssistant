package com.example.pcbuilderassistant.data.repository

import com.example.pcbuilderassistant.data.local.dao.CpuDao
import com.example.pcbuilderassistant.data.local.entity.CpuEntity
import com.example.pcbuilderassistant.data.local.dao.GpuDao
import com.example.pcbuilderassistant.data.local.entity.GpuEntity
import com.example.pcbuilderassistant.data.local.dao.MotherboardDao
import com.example.pcbuilderassistant.data.local.entity.MotherboardEntity


class HardwareRepository(
    private val cpuDao: CpuDao,
    private val gpuDao: GpuDao,
    private val motherboardDao: MotherboardDao
) {

    suspend fun getAllCpus(): List<CpuEntity> {
        return cpuDao.getAll()
    }

    suspend fun insertCpus(cpus: List<CpuEntity>) {
        cpuDao.insertAll(cpus)
    }
    suspend fun getAllGpus() = gpuDao.getAll()

    suspend fun insertGpus(gpus: List<GpuEntity>) {
        gpuDao.insertAll(gpus)
    }
    suspend fun getAllMotherboards() = motherboardDao.getAll()

    suspend fun insertMotherboards(motherboards: List<MotherboardEntity>) =
        motherboardDao.insertAll(motherboards)

    suspend fun getMotherboardsBySocket(socket: String): List<MotherboardEntity> {
        return motherboardDao.getBySocket(socket)
    }
}