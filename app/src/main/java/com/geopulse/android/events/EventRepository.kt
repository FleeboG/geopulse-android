package com.geopulse.android.events

import com.geopulse.android.api.ApiClient
import com.geopulse.android.api.EventCreateRequest
import com.geopulse.android.api.EventResponse

class EventRepository {

    suspend fun getEvents(token: String): List<EventResponse> {
        return ApiClient.api.getEvents(
            authorization = "Bearer $token"
        ).items
    }

    suspend fun createEvent(
        token: String,
        latitude: Double,
        longitude: Double
    ): EventResponse {
        return ApiClient.api.createEvent(
            authorization = "Bearer $token",
            request = EventCreateRequest(
                latitude = latitude,
                longitude = longitude
            )
        )
    }
}