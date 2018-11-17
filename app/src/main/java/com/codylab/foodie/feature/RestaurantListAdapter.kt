package com.codylab.foodie.feature

import android.arch.paging.PagedListAdapter
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.codylab.foodie.core.paging.RestaurantDiffCallback
import com.codylab.foodie.core.zomato.model.search.Restaurant
import com.codylab.foodie.databinding.ItemRestaurantBinding

class RestaurantListAdapter :
    PagedListAdapter<Restaurant, RestaurantListAdapter.RestaurantViewHolder>(RestaurantDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context))
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class RestaurantViewHolder(
        private val binding: ItemRestaurantBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            binding.restaurant = restaurant

            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(restaurant.url)
                it.context.startActivity(intent)
            }
        }
    }
}
