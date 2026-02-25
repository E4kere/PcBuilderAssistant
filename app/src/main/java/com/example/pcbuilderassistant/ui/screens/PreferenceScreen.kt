
package com.example.pcbuilderassistant.ui.screens

import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pcbuilderassistant.ui.viewmodel.HardwareViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pcbuilderassistant.domain.Purpose
import com.example.pcbuilderassistant.domain.Priority
import com.example.pcbuilderassistant.domain.UserPreferences
import com.example.pcbuilderassistant.domain.BuildGenerator
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenceScreen() {
    val hardwareViewModel: HardwareViewModel = viewModel()
    val cpuList by hardwareViewModel.cpus.collectAsState()
    val generatedCpuText by hardwareViewModel.generatedCpu.collectAsState()


    LaunchedEffect(Unit) {
        hardwareViewModel.loadData()
    }



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

                    val preferences = UserPreferences(
                        budget = budget,
                        purpose = selectedPurpose,
                        priority = selectedPriority
                    )

                    hardwareViewModel.generateBuild(preferences)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Generate Build")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(resultText)
            Spacer(modifier = Modifier.height(24.dp))

            Text("CPUs from Database:")

            cpuList.forEach { cpu ->
                Text(text = "• ${cpu.name} (${cpu.price} ₸)")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(generatedCpuText)
        }
    }

    }


