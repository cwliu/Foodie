package com.codylab.foodie.core.zomato.model.search

data class UserRating(
    val aggregate_rating: String,
    val rating_color: String,
    val rating_text: String,
    val votes: String
)