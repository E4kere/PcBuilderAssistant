package com.example.pcbuilderassistant.domain

data class Motherboard(
    val name: String,
    val socket: String,
    val ramType: String,
    val maxRam: Int,
    val price: Int
)
