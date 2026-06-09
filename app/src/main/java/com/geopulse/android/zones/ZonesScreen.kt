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
    viewModel: ZonesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val zones by viewModel.zones.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

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