package com.example.pcbuilderassistant.domain

data class Cpu(
    val name: String,
    val cores: Int,
    val threads: Int,
    val baseClock: Double,
    val socket: String,
    val tdp: Int,
    val price: Int
)

