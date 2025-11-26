package com.example.cosmeteologia.data.repository

import com.example.cosmeteologia.data.network.OpenFoodFactsApiService
import com.example.cosmeteologia.data.network.ProductResponse

class ProductRepository(private val apiService: OpenFoodFactsApiService) {
    suspend fun getProduct(barcode: String): ProductResponse {
        return apiService.getProduct(barcode)
    }
}
