package com.codylab.foodie.usecase

import com.codylab.foodie.core.model.Location
import com.codylab.foodie.core.repository.UserLocationRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetUserLocationUseCase @Inject constructor(
    private val userLocationRepository: UserLocationRepository
) {

    operator fun invoke(): Observable<Location> {
        return Observable.concat(
            userLocationRepository.getLastLocationMaybe().toObservable(),
            userLocationRepository.getLocationUpdates()
        )
    }
}