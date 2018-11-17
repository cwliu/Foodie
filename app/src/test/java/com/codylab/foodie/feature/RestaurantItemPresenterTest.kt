package com.codylab.foodie.feature

import com.codylab.foodie.core.zomato.model.search.Restaurant
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.mockito.Mockito.verify

class RestaurantItemPresenterTest {

    @Test
    fun onRestaurantItemClick() {
        // Arrange
        val url = "www.codylab.com"
        val restaurant:Restaurant = mock()
        whenever(restaurant.url).thenReturn(url)
        val restaurantItemView: RestaurantItemView = mock()
        val presenter = RestaurantPresenter()
        presenter.setup(restaurantItemView, restaurant)

        // Act
        presenter.onItemClick()

        // Assert
        verify(restaurantItemView).goToRestaurantUrl(url)
    }
}