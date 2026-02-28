package com.example.pcbuilderassistant.data.repository

import com.example.pcbuilderassistant.data.local.dao.*
import com.example.pcbuilderassistant.data.local.entity.*

class HardwareRepository(
    private val cpuDao: CpuDao,
    private val gpuDao: GpuDao,
    private val motherboardDao: MotherboardDao,
    private val psuDao: PsuDao
) {

    suspend fun getAllCpus() = cpuDao.getAll()
    suspend fun insertCpus(cpus: List<CpuEntity>) = cpuDao.insertAll(cpus)

    suspend fun getAllGpus() = gpuDao.getAll()
    suspend fun insertGpus(gpus: List<GpuEntity>) = gpuDao.insertAll(gpus)

    suspend fun getAllMotherboards() = motherboardDao.getAll()
    suspend fun insertMotherboards(mbs: List<MotherboardEntity>) =
        motherboardDao.insertAll(mbs)

    suspend fun getMotherboardsBySocket(socket: String) =
        motherboardDao.getBySocket(socket)

    suspend fun getAllPsus() = psuDao.getAll()
    suspend fun insertPsus(psus: List<PsuEntity>) =
        psuDao.insertAll(psus)
}