package com.geopulse.android.api

data class EventResponse(
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val createdAt: String,
    val isInside: Boolean,
    val matchedZones: List<String>,
    val eventType: String
)