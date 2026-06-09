package com.geopulse.android.auth

import com.geopulse.android.api.ApiClient
import com.geopulse.android.api.LoginRequest
import com.geopulse.android.api.RegisterRequest

class AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): String {
        return ApiClient.api.login(
            LoginRequest(
                email = email,
                password = password
            )
        ).accessToken
    }

    suspend fun register(
        email: String,
        password: String
    ): String {
        return ApiClient.api.register(
            RegisterRequest(
                email = email,
                password = password
            )
        ).status
    }
}