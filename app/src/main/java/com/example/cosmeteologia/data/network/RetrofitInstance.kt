package com.example.cosmeteologia.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://world.openbeautyfacts.org/"

    val api: OpenFoodFactsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenFoodFactsApiService::class.java)
    }
}
