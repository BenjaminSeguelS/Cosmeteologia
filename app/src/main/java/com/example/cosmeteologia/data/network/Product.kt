package com.example.cosmeteologia.data.network

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    val product: Product?
)

data class Product(
    @SerializedName("product_name")
    val productName: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    val brands: String?,
    @SerializedName("ingredients_text")
    val ingredientsText: String?
)
