package com.example.cosmeteologia.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface OpenFoodFactsApiService {
    @GET("api/v0/product/{barcode}.json")
    suspend fun getProduct(@Path("barcode") barcode: String): ProductResponse
}
