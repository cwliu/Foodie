package com.codylab.foodie.core.zomato.model.search

import com.codylab.foodie.core.zomato.model.searchimport.SearchRestaurant

data class SearchResponse(
    val results_found: Int,
    val results_shown: Int,
    val results_start: Int,
    val restaurants: List<SearchRestaurant>
)