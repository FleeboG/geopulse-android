package com.geopulse.android.events

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EventHistoryScreen(
    token: String,
    viewModel: EventsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val events by viewModel.events.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(token) {
        viewModel.loadEvents(token)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Event History", style = MaterialTheme.typography.headlineMedium)

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
            items(events) { event ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(event.eventType, style = MaterialTheme.typography.titleMedium)
                        Text("Inside: ${event.isInside}")
                        Text("Lat: ${event.latitude}")
                        Text("Lng: ${event.longitude}")
                        Text("Zones: ${event.matchedZones.joinToString()}")
                        Text(event.createdAt)
                    }
                }
            }
        }
    }
}
