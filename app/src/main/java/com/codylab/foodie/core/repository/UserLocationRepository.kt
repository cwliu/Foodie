package com.codylab.foodie.core.repository

import android.annotation.SuppressLint
import com.codylab.foodie.core.model.Location
import com.google.android.gms.location.*
import io.reactivex.Maybe
import io.reactivex.Observable
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@Singleton
class UserLocationRepository @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient
) {

    private var locationCallback: LocationCallback? = null

    @SuppressLint("MissingPermission")
    suspend fun getLastKnownLocationCoroutine(): Location? {
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

    @SuppressLint("MissingPermission")
    fun getLocationUpdates(): Observable<Location> {
        return Observable.create<Location> { emitter ->
            val locationRequest = LocationRequest()
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            locationRequest.interval = 10_000

            locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    val locations = result.locations
                    if (locations.size == 0) {
                        return
                    }

                    val lastLocation = locations.last()
                    val location = Location(lastLocation.latitude, lastLocation.longitude, lastLocation.accuracy)
                    emitter.onNext(location)
                }

                override fun onLocationAvailability(isAvailability: LocationAvailability) {
                    Timber.i("onLocationAvailability: $isAvailability")
                    if (!isAvailability.isLocationAvailable) {
                        emitter.onError(IOException("Can't get user's location, until something changes in the device's settings or environment"))
                    }
                }
            }
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
        }.doOnDispose {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        }
    }
}