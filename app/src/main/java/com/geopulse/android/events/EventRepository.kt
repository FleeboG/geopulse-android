package com.geopulse.android.events

import com.geopulse.android.api.ApiClient
import com.geopulse.android.api.EventResponse

class EventRepository {

    suspend fun getEvents(token: String): List<EventResponse> {
        return ApiClient.api.getEvents(
            authorization = "Bearer $token"
        ).items
    }
}