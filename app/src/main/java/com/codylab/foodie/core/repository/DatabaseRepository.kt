package com.codylab.foodie.core.repository

import com.codylab.foodie.core.room.AppDatabase
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepository @Inject constructor(
    private val appDatabase: AppDatabase
) {

    fun deleteAll(): Completable {
        return Completable.create {
            appDatabase.restaurantDao().deleteAll()
            it.onComplete()
        }.subscribeOn(Schedulers.io())
    }
}