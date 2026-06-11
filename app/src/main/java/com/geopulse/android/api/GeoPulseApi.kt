package com.geopulse.android.api

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Header

interface GeoPulseApi {

    @POST("api/v1/auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): RegisterResponse

    @POST("api/v1/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): AuthResponse

    @POST("api/v1/zones")
    suspend fun createZone(
        @Header("Authorization") authorization: String,
        @Body request: ZoneCreateRequest
    ): ZoneResponse

    @POST("api/v1/events")
    suspend fun createEvent(
        @Header("Authorization") authorization: String,
        @Body request: EventCreateRequest
    ): EventResponse

    @GET("api/v1/zones")
    suspend fun getZones(
        @Header("Authorization") authorization: String
    ): List<ZoneResponse>

    @GET("api/v1/events")
    suspend fun getEvents(
        @Header("Authorization") authorization: String
    ): EventPageResponse


}