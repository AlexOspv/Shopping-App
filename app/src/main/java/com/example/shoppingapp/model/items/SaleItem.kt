package com.example.shoppingapp.model.items

import com.example.shoppingapp.model.base.ListItem

data class SaleItem(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Int
) : ListItem {
    override val itemId: Long = hashCode().toLong()
}
