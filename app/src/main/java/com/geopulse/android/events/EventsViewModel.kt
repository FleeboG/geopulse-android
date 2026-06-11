package com.geopulse.android.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geopulse.android.api.EventResponse
import com.geopulse.android.location.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EventsViewModel(
    private val repository: EventRepository = EventRepository()
) : ViewModel() {

    private val _events = MutableStateFlow<List<EventResponse>>(emptyList())
    val events: StateFlow<List<EventResponse>> = _events

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadEvents(token: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _error.value = null
                _events.value = repository.getEvents(token)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun sendCurrentLocation(
        token: String,
        locationRepository: LocationRepository
    ) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _error.value = null

                val (latitude, longitude) = locationRepository.getCurrentLocation()

                repository.createEvent(
                    token = token,
                    latitude = latitude,
                    longitude = longitude
                )

                _events.value = repository.getEvents(token)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}