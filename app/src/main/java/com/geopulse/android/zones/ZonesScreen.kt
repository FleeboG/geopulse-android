package com.geopulse.android.zones

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ZonesScreen(
    token: String,
    onGoToEvents: () -> Unit,
    onLogout: () -> Unit,
    viewModel: ZonesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val zones by viewModel.zones.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    var showCreateForm by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf("36.1867") }
    var longitude by remember { mutableStateOf("-94.1288") }
    var radiusM by remember { mutableStateOf("150") }

    LaunchedEffect(token) {
        viewModel.loadZones(token)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Zones", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { showCreateForm = !showCreateForm },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (showCreateForm) "Cancel" else "+ Create Zone")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (showCreateForm) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Zone name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = latitude,
                onValueChange = { latitude = it },
                label = { Text("Latitude") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = longitude,
                onValueChange = { longitude = it },
                label = { Text("Longitude") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = radiusM,
                onValueChange = { radiusM = it },
                label = { Text("Radius meters") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    viewModel.createZone(
                        token = token,
                        name = name,
                        latitude = latitude.toDoubleOrNull() ?: 0.0,
                        longitude = longitude.toDoubleOrNull() ?: 0.0,
                        radiusM = radiusM.toDoubleOrNull() ?: 0.0,
                        onSuccess = {
                            showCreateForm = false
                            name = ""
                        }
                    )
                },
                enabled = !loading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Zone")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = onGoToEvents,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Event History")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (loading) {
            CircularProgressIndicator()
        }

        error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(zones) { zone ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(zone.name, style = MaterialTheme.typography.titleMedium)
                        Text("Lat: ${zone.latitude}")
                        Text("Lng: ${zone.longitude}")
                        Text("Radius: ${zone.radiusM}m")
                    }
                }
            }
        }
    }
}