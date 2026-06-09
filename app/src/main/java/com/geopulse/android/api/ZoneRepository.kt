package com.geopulse.android.zones

import com.geopulse.android.api.ApiClient
import com.geopulse.android.api.ZoneCreateRequest
import com.geopulse.android.api.ZoneResponse

class ZoneRepository {

    suspend fun getZones(token: String): List<ZoneResponse> {
        return ApiClient.api.getZones(
            authorization = "Bearer $token"
        )
    }

    suspend fun createZone(
        token: String,
        name: String,
        latitude: Double,
        longitude: Double,
        radiusM: Double
    ): ZoneResponse {
        return ApiClient.api.createZone(
            authorization = "Bearer $token",
            request = ZoneCreateRequest(
                name = name,
                latitude = latitude,
                longitude = longitude,
                radiusM = radiusM
            )
        )
    }
}