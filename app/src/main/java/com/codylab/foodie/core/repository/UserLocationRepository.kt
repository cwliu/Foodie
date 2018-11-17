package com.codylab.foodie.core.repository

import android.annotation.SuppressLint
import com.codylab.finefood.core.model.Location
import com.google.android.gms.location.FusedLocationProviderClient
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@Singleton
class UserLocationRepository @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient
) {
    @SuppressLint("MissingPermission")
    suspend fun getLastKnownLocation(): Location? {
        return suspendCoroutine { continuation ->
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location == null) {
                        continuation.resume(null)
                        return@addOnSuccessListener
                    }
                    continuation.resume(Location(location.latitude, location.longitude, location.accuracy))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }
}