package com.geopulse.android.api

import retrofit2.http.Body
import retrofit2.http.POST

interface GeoPulseApi {

    @POST("api/v1/auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): RegisterResponse

    @POST("api/v1/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): AuthResponse
}