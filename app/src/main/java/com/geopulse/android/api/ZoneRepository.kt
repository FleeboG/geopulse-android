package com.geopulse.android.zones

import com.geopulse.android.api.ApiClient
import com.geopulse.android.api.ZoneResponse

class ZoneRepository {

    suspend fun getZones(token: String): List<ZoneResponse> {
        return ApiClient.api.getZones(
            authorization = "Bearer $token"
        )
    }
}