package com.example.shoppingapp.view

data class LatestItem(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Int
) : ListItem