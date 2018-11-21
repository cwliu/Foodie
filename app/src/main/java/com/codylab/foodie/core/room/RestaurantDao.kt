package com.codylab.foodie.core.room

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface RestaurantDao {

    @Query("SELECT * from restaurants ORDER BY rating DESC")
    fun getAll(): DataSource.Factory<Int, RestaurantEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg restaurant: RestaurantEntity)

    @Query("DELETE from restaurants")
    fun deleteAll()
}