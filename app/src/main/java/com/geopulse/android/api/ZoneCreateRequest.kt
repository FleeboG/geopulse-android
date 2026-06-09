package com.geopulse.android.api

data class ZoneCreateRequest(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val radiusM: Double
)