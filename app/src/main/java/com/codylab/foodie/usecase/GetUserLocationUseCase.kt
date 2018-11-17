package com.codylab.foodie.usecase

import com.codylab.finefood.core.model.Location
import com.codylab.foodie.core.repository.UserLocationRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetUserLocationUseCase @Inject constructor(
    val userLocationRepository: UserLocationRepository
) {

    operator fun invoke(): Observable<Location> {
        return Observable.concat(
            userLocationRepository.getLastLocationMaybe().toObservable(),
            userLocationRepository.getLocationUpdates()
        )
    }
}