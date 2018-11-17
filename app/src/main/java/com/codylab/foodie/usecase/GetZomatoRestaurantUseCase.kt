package com.codylab.foodie.usecase

import com.codylab.foodie.core.repository.ZomatoRestaurantRepository
import com.codylab.foodie.core.zomato.model.search.Restaurant
import io.reactivex.Observable
import javax.inject.Inject

class GetZomatoRestaurantUseCase @Inject constructor(
    val userLocationUseCase: GetUserLocationUseCase,
    private val zomatoRestaurantRepository: ZomatoRestaurantRepository
) {
    operator fun invoke(): Observable<List<Restaurant>> {
        return userLocationUseCase().flatMap { location ->
            zomatoRestaurantRepository.getRestaurants(location).toObservable()
        }
    }
}