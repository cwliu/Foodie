package com.codylab.foodie.core.zomato.model.search

import retrofit2.http.Field

data class Restaurant(
    @Field("R") val R: Rid,
    val apikey: String,
    val average_cost_for_two: Int,
    val book_again_url: String,
    val book_form_web_view_url: String,
    val cuisines: String,
    val currency: String,
    val deeplink: String,
    val establishment_types: List<Any>,
    val events_url: String,
    val featured_image: String,
    val has_online_delivery: Int,
    val has_table_booking: Int,
    val id: String,
    val include_bogo_offers: Boolean,
    val is_book_form_web_view: Int,
    val is_delivering_now: Int,
    val is_table_reservation_supported: Int,
    val is_zomato_book_res: Int,
    val location: Location,
    val menu_url: String,
    val mezzo_provider: String,
    val name: String,
    val offers: List<Any>,
    val opentable_support: Int,
    val photos_url: String,
    val price_range: Int,
    val switch_to_order_menu: Int,
    val thumb: String,
    val url: String,
    val user_rating: UserRating
)