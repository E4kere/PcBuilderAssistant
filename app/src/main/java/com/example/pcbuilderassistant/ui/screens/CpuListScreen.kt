package com.example.pcbuilderassistant.ui.screens
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pcbuilderassistant.data.FakeData
import com.example.pcbuilderassistant.domain.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.fillMaxSize


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CpuListScreen() {

    var selectedMotherboard by remember { mutableStateOf<Motherboard?>(null) }
    var selectedGpu by remember { mutableStateOf<Gpu?>(null) }
    var selectedPsu by remember { mutableStateOf<Psu?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("PC Builder Assistant") }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {


            // ---------- CPU LIST ----------
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(FakeData.cpuList) { cpu ->

                    Card(modifier = Modifier.padding(8.dp)) {
                        Column(modifier = Modifier.padding(12.dp)) {

                            Text(cpu.name)
                            Text("Cores: ${cpu.cores} / Threads: ${cpu.threads}")
                            Text("Base clock: ${cpu.baseClock} GHz")
                            Text("Socket: ${cpu.socket}")
                            Text("TDP: ${cpu.tdp}W")

                            // ---- SOCKET COMPATIBILITY ----
                            selectedMotherboard?.let { motherboard ->
                                val compatible =
                                    CompatibilityChecker.isCpuCompatible(cpu, motherboard)

                                Text(
                                    if (compatible)
                                        "Compatible with ${motherboard.name}"
                                    else
                                        "Not compatible with ${motherboard.name}"
                                )
                            }

                            // ---- POWER CHECK ----
                            if (selectedGpu != null && selectedPsu != null) {

                                val requiredPower =
                                    PowerCalculator.calculateRequiredPower(cpu, selectedGpu!!)
                                val enough =
                                    PowerCalculator.isEnough(selectedPsu!!, requiredPower)

                                Text("Required power: ${requiredPower}W")

                                Text(
                                    if (enough)
                                        "Power supply is enough"
                                    else
                                        "WARNING: Not enough PSU power"
                                )
                            }
                        }
                    }
                }
            }

            // ---------- MOTHERBOARD ----------
            Text("Select motherboard:", modifier = Modifier.padding(8.dp))

            FakeData.motherboardList.forEach { board ->
                Button(
                    onClick = { selectedMotherboard = board },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            if (selectedMotherboard == board) Color(0xFF4CAF50)
                            else ButtonDefaults.buttonColors().containerColor
                    )
                ) {
                    Text(board.name)
                }
            }


            // ---------- GPU ----------
            Text("Select GPU:", modifier = Modifier.padding(8.dp))

            FakeData.gpuList.forEach { gpu ->
                Button(
                    onClick = { selectedGpu = gpu },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            if (selectedGpu == gpu) Color(0xFF4CAF50)
                            else ButtonDefaults.buttonColors().containerColor
                    )
                ) {
                    Text(gpu.name)
                }
            }


            // ---------- PSU ----------
            Text("Select Power Supply:", modifier = Modifier.padding(8.dp))

            FakeData.psuList.forEach { psu ->
                Button(
                    onClick = { selectedPsu = psu },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            if (selectedPsu == psu) Color(0xFF4CAF50)
                            else ButtonDefaults.buttonColors().containerColor
                    )
                ) {
                    Text("${psu.name} (${psu.wattage}W)")
                }
            }

        }
        // -------- BUILD SUMMARY --------

        selectedMotherboard?.let { board ->
            selectedGpu?.let { gpu ->
                selectedPsu?.let { psu ->

                    Text(
                        text = "Current Build:",
                        modifier = Modifier.padding(12.dp)
                    )

                    Card(modifier = Modifier.padding(8.dp)) {
                        Column(modifier = Modifier.padding(12.dp)) {

                            Text("Motherboard: ${board.name}")
                            Text("GPU: ${gpu.name}")
                            Text("PSU: ${psu.name}")

                            val sampleCpu = FakeData.cpuList.first()
                            val requiredPower =
                                PowerCalculator.calculateRequiredPower(sampleCpu, gpu)

                            Text("Estimated power usage: ${requiredPower}W")

                            if (psu.wattage < requiredPower)
                                Text("⚠ Upgrade PSU recommended")
                            else
                                Text("System is balanced")
                        }
                    }
                }
            }
        }

    }
}