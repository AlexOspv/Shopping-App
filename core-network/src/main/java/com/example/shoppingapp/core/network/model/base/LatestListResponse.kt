package com.example.shoppingapp.core.network.model.base

import com.example.shoppingapp.core.network.model.LatestItemsDto
import com.google.gson.annotations.SerializedName

data class LatestListResponse(
    @SerializedName("latest") val latest: List<LatestItemsDto>
)