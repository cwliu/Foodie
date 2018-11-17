package com.codylab.foodie.feature

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.res.Resources
import com.codylab.foodie.R
import com.codylab.foodie.usecase.GetUserLocationUseCase
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.*
import org.junit.rules.TestRule

class RestaurantListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun showNoPermissionError() {
        // Arrange
        val resources: Resources = mock()
        val getUserLocation: GetUserLocationUseCase = mock()
        val viewModel = RestaurantListViewModel(
            resources, getUserLocation
        )
        val testObserver = viewModel.uiModel.testObserver()
        val message = "Hi Testing"
        whenever(resources.getString(R.string.error_no_location_permission)).thenReturn(message)

        // Act
        viewModel.showNoPermissionError()

        // Assert
        val observedValues = testObserver.observedValues
        val uiModel = observedValues.last()

        Assert.assertEquals(observedValues.size, 1)
        Assert.assertEquals(uiModel?.message?.getDataIfNotHandled(), message)
    }
}