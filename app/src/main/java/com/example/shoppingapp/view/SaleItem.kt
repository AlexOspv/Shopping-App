package com.example.shoppingapp.view

data class SaleItem(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Int
) : ListItem
