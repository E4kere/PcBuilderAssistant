package com.example.pcbuilderassistant.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pcbuilderassistant.domain.*
import com.example.pcbuilderassistant.ui.BuildResultCard
import com.example.pcbuilderassistant.ui.viewmodel.HardwareViewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenceScreen() {

    val hardwareViewModel: HardwareViewModel = viewModel()
    val resultText by hardwareViewModel.generatedCpu.collectAsState()

    LaunchedEffect(Unit) {
        hardwareViewModel.loadData()
    }

    var budgetText by remember { mutableStateOf("") }
    var selectedPurpose by remember { mutableStateOf(Purpose.GAMING) }
    var selectedPriority by remember { mutableStateOf(Priority.BALANCED) }
    var selectedResolution by remember { mutableStateOf(Resolution.FULL_HD) }
    var selectedUpgradeMode by remember { mutableStateOf(UpgradeMode.NONE) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("PC Builder Assistant") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            // Budget
            OutlinedTextField(
                value = budgetText,
                onValueChange = { budgetText = it },
                label = { Text("Budget") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Purpose
            Text("Purpose")
            Spacer(modifier = Modifier.height(8.dp))

            Purpose.values().forEach { purpose ->
                Button(
                    onClick = { selectedPurpose = purpose },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            if (selectedPurpose == purpose)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(purpose.name)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Priority
            Text("Priority")
            Spacer(modifier = Modifier.height(8.dp))

            Priority.values().forEach { priority ->
                Button(
                    onClick = { selectedPriority = priority },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            if (selectedPriority == priority)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(priority.name)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Resolution
            Text("Resolution")
            Spacer(modifier = Modifier.height(8.dp))

            Resolution.values().forEach { resolution ->
                Button(
                    onClick = { selectedResolution = resolution },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            if (selectedResolution == resolution)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(resolution.name)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Upgrade Mode
            Text("Upgrade Mode")
            Spacer(modifier = Modifier.height(8.dp))

            UpgradeMode.values().forEach { mode ->
                Button(
                    onClick = { selectedUpgradeMode = mode },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            if (selectedUpgradeMode == mode)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(mode.name)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Generate button
            Button(
                onClick = {
                    val budget = budgetText.toIntOrNull() ?: return@Button

                    val preferences = UserPreferences(
                        budget = budget,
                        purpose = selectedPurpose,
                        priority = selectedPriority,
                        resolution = selectedResolution,
                        upgradeMode = selectedUpgradeMode
                    )

                    hardwareViewModel.generateBuild(preferences)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Generate Build")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Result
            if (resultText.isNotBlank()) {
                BuildResultCard(resultText)
            }
        }
    }
}