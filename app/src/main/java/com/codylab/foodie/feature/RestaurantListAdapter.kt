package com.codylab.foodie.feature

import android.arch.paging.PagedListAdapter
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.codylab.foodie.core.paging.RestaurantDiffCallback
import com.codylab.foodie.core.room.RestaurantEntity
import com.codylab.foodie.databinding.ItemRestaurantBinding

class RestaurantListAdapter :
    PagedListAdapter<RestaurantEntity, RestaurantListAdapter.RestaurantViewHolder>(RestaurantDiffCallback) {

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
    ) : RecyclerView.ViewHolder(binding.root), RestaurantItemView {

        private val restaurantPresenter = RestaurantPresenter()

        fun bind(restaurant: RestaurantEntity) {
            restaurantPresenter.setup(this, restaurant)

            binding.restaurant = restaurant
            binding.root.setOnClickListener {
                restaurantPresenter.onItemClick()
            }
        }

        override fun goToRestaurantUrl(url: String) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            itemView.context.startActivity(intent)
        }
    }
}

interface RestaurantItemView {
    fun goToRestaurantUrl(url: String)
}

class RestaurantPresenter {

    lateinit var view: RestaurantItemView
    lateinit var restaurant: RestaurantEntity

    fun setup(view: RestaurantItemView, restaurant: RestaurantEntity) {
        this.view = view
        this.restaurant = restaurant
    }

    fun onItemClick() {
        view.goToRestaurantUrl(restaurant.url)
    }
}
