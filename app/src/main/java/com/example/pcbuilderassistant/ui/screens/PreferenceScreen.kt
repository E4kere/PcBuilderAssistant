
package com.example.pcbuilderassistant.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pcbuilderassistant.domain.Purpose
import com.example.pcbuilderassistant.domain.Priority
import com.example.pcbuilderassistant.domain.UserPreferences
import com.example.pcbuilderassistant.domain.BuildGenerator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenceScreen() {

    var budgetText by remember { mutableStateOf("") }
    var selectedPurpose by remember { mutableStateOf(Purpose.GAMING) }
    var selectedPriority by remember { mutableStateOf(Priority.BALANCED) }

    var resultText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("PC Builder Assistant") })
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Text("Budget")

            OutlinedTextField(
                value = budgetText,
                onValueChange = { budgetText = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Purpose")

            Purpose.values().forEach { purpose ->
                Button(
                    onClick = { selectedPurpose = purpose },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(purpose.name)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Priority")

            Priority.values().forEach { pr ->
                Button(
                    onClick = { selectedPriority = pr },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(pr.name)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {

                    val budget = budgetText.toIntOrNull() ?: return@Button

                    val pref = UserPreferences(
                        budget = budget,
                        purpose = selectedPurpose,
                        priority = selectedPriority
                    )

                    val build = BuildGenerator.generate(pref)

                    resultText =
                        """
                        CPU: ${build.cpu.name}
                        GPU: ${build.gpu.name}
                        Motherboard: ${build.motherboard.name}
                        PSU: ${build.psu.name}
                        Balance:
                        ${build.balance}
                        Reason:
                        ${build.explanation}
                        """.trimIndent()
                  },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Generate Build")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(resultText)
        }
    }
}
