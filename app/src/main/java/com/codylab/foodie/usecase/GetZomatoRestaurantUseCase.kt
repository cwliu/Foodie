package com.codylab.foodie.usecase

import com.codylab.finefood.core.zomato.model.search.SearchRestaurant
import com.codylab.foodie.core.repository.ZomatoRestaurantRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetZomatoRestaurantUseCase @Inject constructor(
    val userLocationUseCase: GetUserLocationUseCase,
    private val zomatoRestaurantRepository: ZomatoRestaurantRepository
) {
    operator fun invoke(): Observable<List<SearchRestaurant>> {
        return userLocationUseCase().flatMap { location ->
            zomatoRestaurantRepository.getRestaurants(location).toObservable()
        }
    }
}