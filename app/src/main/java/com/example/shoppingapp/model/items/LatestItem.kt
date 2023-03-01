package com.example.shoppingapp.model.items

import com.example.shoppingapp.model.base.ListItem

data class LatestItem(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Int
) : ListItem {
    override val itemId: Long = hashCode().toLong()
}