package com.example.shoppingapp.core.network.api

import com.example.shoppingapp.core.network.model.base.LatestListResponse
import com.example.shoppingapp.core.network.model.base.SaleListResponse
import retrofit2.http.GET

interface ItemsApi {

    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun latestItems(): LatestListResponse

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun saleItems(): SaleListResponse

}