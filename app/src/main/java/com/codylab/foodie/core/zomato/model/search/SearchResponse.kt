package com.codylab.finefood.core.zomato.model.search

data class SearchResponse(
    val restaurants: List<SearchRestaurant>,
    val results_found: Int,
    val results_shown: Int,
    val results_start: Int
)