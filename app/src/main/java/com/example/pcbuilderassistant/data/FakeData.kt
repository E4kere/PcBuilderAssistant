package com.example.pcbuilderassistant.data

import com.example.pcbuilderassistant.domain.*

object FakeData {

    val cpuList = listOf(
        Cpu("Ryzen 5 5600X", 6, 12, 3.7, "AM4", 65, 85000),
        Cpu("Ryzen 7 5800X", 8, 16, 3.8, "AM4", 105, 130000),
        Cpu("Intel i5-12400F", 6, 12, 2.5, "LGA1700", 65, 95000),
        Cpu("Intel i7-12700K", 12, 20, 3.6, "LGA1700", 125, 180000)
    )

    val gpuList = listOf(
        Gpu("RTX 3060", 12, 170, 170000),
        Gpu("RX 6600", 8, 132, 150000),
        Gpu("RTX 4070", 12, 200, 320000),
        Gpu("RTX 4090", 24, 450, 900000)
    )

    val motherboardList = listOf(
        Motherboard("MSI B450 Tomahawk", "AM4", "DDR4", 128, 60000),
        Motherboard("ASUS ROG Strix B550-F", "AM4", "DDR4", 128, 85000),
        Motherboard("Gigabyte B660M DS3H", "LGA1700", "DDR4", 128, 70000),
        Motherboard("ASUS Prime Z690-P", "LGA1700", "DDR5", 128, 120000)
    )

    val psuList = listOf(
        Psu("500W Bronze", 500, 25000),
        Psu("650W Gold", 650, 40000),
        Psu("750W Gold", 750, 55000),
        Psu("1000W Platinum", 1000, 90000)
    )
}
