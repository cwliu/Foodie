package com.codylab.foodie.core.paging

import android.support.v7.util.DiffUtil
import com.codylab.foodie.core.zomato.model.search.Restaurant

object RestaurantDiffCallback: DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.id == newItem.id
    }
}