package com.example.pcbuilderassistant.data.repository

import com.example.pcbuilderassistant.data.local.dao.CpuDao
import com.example.pcbuilderassistant.data.local.entity.CpuEntity
import com.example.pcbuilderassistant.data.local.dao.GpuDao
import com.example.pcbuilderassistant.data.local.entity.GpuEntity

class HardwareRepository(
    private val cpuDao: CpuDao,
    private val gpuDao: GpuDao
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
}