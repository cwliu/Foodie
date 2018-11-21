package com.codylab.foodie.core.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.codylab.foodie.core.zomato.model.search.Restaurant

@Entity(tableName = "restaurants")
data class RestaurantEntity(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "featured_image") var featuredImage: String,
    @ColumnInfo(name = "rating") var rating: Float,
    @ColumnInfo(name = "url") var url: String
)

fun Restaurant.toRestaurantEntity(): RestaurantEntity {
    return RestaurantEntity(this.id, this.name, this.featured_image, this.user_rating.aggregate_rating.toFloat(), this.url)
}