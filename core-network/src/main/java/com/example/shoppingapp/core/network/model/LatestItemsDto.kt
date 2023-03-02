package com.example.shoppingapp.core.network.model

import com.google.gson.annotations.SerializedName

data class LatestItemsDto(
    @SerializedName("name") val name: String,
    @SerializedName("category") val category: String,
    @SerializedName("price") val price: Double,
    @SerializedName("image_url") val image_url: String,
)