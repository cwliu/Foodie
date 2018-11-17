package com.codylab.foodie.core.repository

import android.annotation.SuppressLint
import com.codylab.finefood.core.model.Location
import com.google.android.gms.location.FusedLocationProviderClient
import io.reactivex.Maybe
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@Singleton
class LastKnownLocationRepository @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient
) {
    @SuppressLint("MissingPermission")
    suspend fun getLastLocationCoroutine(): Location? {
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

    @SuppressLint("MissingPermission")
    fun getLastLocationMaybe(): Maybe<Location> {
        return Maybe.create { emitter ->
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener {
                    emitter.onSuccess(Location(it.latitude, it.longitude, it.accuracy))
                }.addOnFailureListener {
                    emitter.onError(it)
                }.addOnCanceledListener {
                    emitter.onComplete()
                }
        }
    }
}