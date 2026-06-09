package com.geopulse.android.zones

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geopulse.android.api.ZoneResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ZonesViewModel(
    private val repository: ZoneRepository = ZoneRepository()
) : ViewModel() {

    private val _zones = MutableStateFlow<List<ZoneResponse>>(emptyList())
    val zones: StateFlow<List<ZoneResponse>> = _zones

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadZones(token: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _error.value = null
                _zones.value = repository.getZones(token)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}