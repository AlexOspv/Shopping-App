package com.example.shoppingapp.model.items

import com.example.shoppingapp.model.base.ListItem

data class ItemsHorizontalItem(
    val title: String,
    val viewAllButton: String = "View all",
    val list: List<ListItem>
) : ListItem {
    override val itemId: Long = title.hashCode().toLong()
}
