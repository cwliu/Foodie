package com.codylab.foodie.core.dagger

import android.arch.persistence.room.Room
import android.content.Context
import com.codylab.foodie.core.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "foodie").build()
    }
}