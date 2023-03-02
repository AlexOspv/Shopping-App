package com.example.shoppingapp.core.network.di

import com.example.shoppingapp.core.network.BuildConfig
import com.example.shoppingapp.core.network.api.ItemsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface NetworkComponent {

    companion object {
        private const val BASE_URL = "https://run.mocky.io/v3/"
        fun createApi(): ItemsApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level =
                            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                            else HttpLoggingInterceptor.Level.NONE
                    })
                    .build()
            )
            .build()
            .create(ItemsApi::class.java)
    }

}