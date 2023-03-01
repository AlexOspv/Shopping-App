package com.example.shoppingapp.model.items

import com.example.shoppingapp.model.base.ListItem

data class CategoryItem(
    val name: String,
    val resId: Int
) : ListItem {
    override val itemId: Long = hashCode().toLong()
}
