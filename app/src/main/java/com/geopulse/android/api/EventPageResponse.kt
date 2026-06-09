package com.geopulse.android.api

data class EventPageResponse(
    val items: List<EventResponse>,
    val count: Int,
    val limit: Int
)