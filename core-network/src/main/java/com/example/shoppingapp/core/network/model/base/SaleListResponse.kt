package com.example.shoppingapp.core.network.model.base

import com.example.shoppingapp.core.network.model.SaleItemsDto
import com.google.gson.annotations.SerializedName

data class SaleListResponse(
    @SerializedName("flash_sale") val sale: List<SaleItemsDto>
)
