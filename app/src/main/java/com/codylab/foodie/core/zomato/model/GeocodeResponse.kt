package com.codylab.foodie.core.zomato.model

import retrofit2.http.Field

data class GeocodeResponse(
    val zomatoLocation: ZomatoLocation,
    val popularity: Popularity,
    val link: String,
    val nearby_restaurants: List<NearbyRestaurant>
) {

    data class ZomatoLocation(
        val entity_type: String,
        val entity_id: Int,
        val title: String,
        val latitude: String,
        val longitude: String,
        val city_id: Int,
        val city_name: String,
        val country_id: Int,
        val country_name: String
    )

    data class Popularity(
        val popularity: String,
        val nightlife_index: String,
        val nearby_res: List<String>,
        val top_cuisines: List<String>,
        val popularity_res: String,
        val nightlife_res: String,
        val subzone: String,
        val subzone_id: Int,
        val city: String
    )

    data class NearbyRestaurant(
        val restaurant: ZomatoRestaurant
    )
}

data class ZomatoRestaurant(
    @Field("R") val R: Rid,
    val apikey: String,
    val id: String,
    val name: String,
    val url: String,
    val location: Location,
    val switch_to_order_menu: Int,
    val cuisines: String,
    val average_cost_for_two: Int,
    val price_range: Int,
    val currency: String,
    val offers: List<Any>,
    val opentable_support: Int,
    val is_zomato_book_res: Int,
    val mezzo_provider: String,
    val is_book_form_web_view: Int,
    val book_form_web_view_url: String,
    val book_again_url: String,
    val thumb: String,
    val user_rating: UserRating,
    val photos_url: String,
    val menu_url: String,
    val featured_image: String,
    val has_online_delivery: Int,
    val is_delivering_now: Int,
    val include_bogo_offers: Boolean,
    val deeplink: String,
    val is_table_reservation_supported: Int,
    val has_table_booking: Int,
    val events_url: String
) {

    data class UserRating(
        val aggregate_rating: String,
        val rating_text: String,
        val rating_color: String,
        val votes: String
    )

    data class Rid(
        val res_id: Int
    )

    data class Location(
        val address: String,
        val locality: String,
        val city: String,
        val city_id: Int,
        val latitude: String,
        val longitude: String,
        val zipcode: String,
        val country_id: Int,
        val locality_verbose: String
    )

    override fun toString(): String {
        return "${this.name}: ${this.user_rating.aggregate_rating}"
    }
}
