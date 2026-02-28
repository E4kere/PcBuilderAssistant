package com.example.pcbuilderassistant.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BudgetUsageBar(totalPrice: Int, budget: Int) {

    val usage = totalPrice.toFloat() / budget

    val color = when {
        usage < 0.8f -> Color(0xFF4CAF50)   // Зеленый
        usage < 0.95f -> Color(0xFFFFC107)  // Желтый
        else -> Color(0xFFF44336)           // Красный
    }

    LinearProgressIndicator(
        progress = usage,
        color = color,
        modifier = Modifier.fillMaxWidth()
    )
}