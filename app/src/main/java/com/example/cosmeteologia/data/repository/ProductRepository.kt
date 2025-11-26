package com.example.cosmeteologia.data.repository

import com.example.cosmeteologia.data.network.OpenBeautyFactsApiService
import com.example.cosmeteologia.data.network.ProductResponse

class ProductRepository(private val apiService: OpenBeautyFactsApiService) {
    suspend fun getProduct(barcode: String): ProductResponse {
        return apiService.getProduct(barcode)
    }
}
