package com.geopulse.android.api

data class ZoneResponse(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val radiusM: Double,
    val createdAt: String
)