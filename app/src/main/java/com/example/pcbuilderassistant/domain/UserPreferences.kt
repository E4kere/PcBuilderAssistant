package com.example.pcbuilderassistant.domain

data class UserPreferences(
    val budget: Int,
    val purpose: Purpose,
    val priority: Priority,
    val resolution: Resolution,
    val upgradeMode: UpgradeMode
)
