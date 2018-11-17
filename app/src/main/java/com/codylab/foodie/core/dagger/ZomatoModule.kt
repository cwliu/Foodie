package com.codylab.foodie.core.dagger

import com.codylab.finefood.core.zomato.ZomatoApi
import com.codylab.foodie.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val ZOMATO_API_BASE_URL = "https://developers.zomato.com/api/v2.1/"

@Module
class ZomatoModule {

    @Singleton
    @Provides
    fun provideZomatoApi(): ZomatoApi {

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("user-key", BuildConfig.ZOMATO_API_KEY)
                .build()

            chain.proceed(request)
        }.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        return Retrofit.Builder()
            .baseUrl(ZOMATO_API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(ZomatoApi::class.java)
    }
}