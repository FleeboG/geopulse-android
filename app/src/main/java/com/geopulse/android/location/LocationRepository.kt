package com.geopulse.android.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await

class LocationRepository(
    private val context: Context
) {
    private val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(context)

    suspend fun getCurrentLocation(): Pair<Double, Double> {
        val fineGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val coarseGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!fineGranted && !coarseGranted) {
            throw IllegalStateException("Location permission not granted")
        }

        val location = fusedLocationClient.lastLocation.await()
            ?: throw IllegalStateException("Current location unavailable")

        return location.latitude to location.longitude
    }
}