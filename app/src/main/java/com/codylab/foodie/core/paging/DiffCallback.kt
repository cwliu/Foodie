package com.codylab.foodie.core.paging

import android.support.v7.util.DiffUtil
import com.codylab.foodie.core.room.RestaurantEntity

object RestaurantDiffCallback: DiffUtil.ItemCallback<RestaurantEntity>() {
    override fun areItemsTheSame(oldItem: RestaurantEntity, newItem: RestaurantEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RestaurantEntity, newItem: RestaurantEntity): Boolean {
        return oldItem.id == newItem.id
    }
}