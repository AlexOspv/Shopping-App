package com.example.shoppingapp.view

data class ItemsHorizontalItem(
    val title: String,
    val viewAllButton: String = "View all",
    val list: List<ListItem>
) : ListItem
