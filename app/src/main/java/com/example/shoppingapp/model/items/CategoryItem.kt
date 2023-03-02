package com.example.shoppingapp.model.items

import android.graphics.drawable.ColorDrawable
import com.example.shoppingapp.model.base.ListItem

data class CategoryItem(
    val name: String,
    val image: Int
) : ListItem {
    override val itemId: Long = hashCode().toLong()
}
