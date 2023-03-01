package com.example.shoppingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppingapp.data.database.Page1Delegates
import com.example.shoppingapp.databinding.ActivityPage1Binding
import com.example.shoppingapp.view.*
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class Page1 : AppCompatActivity() {

    private lateinit var binding: ActivityPage1Binding

    private val adapter = ListDelegationAdapter(
        Page1Delegates.itemsHorizontalDelegate
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPage1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            recyclerView.adapter = adapter

            adapter.apply {
                items = listOf(
                    ItemsHorizontalItem(
                        title = " ",
                        viewAllButton = " ",
                        list = IntRange(1, 20).map{CategoryItem("Phones", 1)}
                    ),
                    ItemsHorizontalItem(
                        title = "Latest",
                        viewAllButton = "View all",
                        list = IntRange(1, 20).map{LatestItem("Phones", "none", "Samsung", 100)}
                    ),
                    ItemsHorizontalItem(
                        title = "Sale",
                        viewAllButton = "View all",
                        list = IntRange(1, 20).map{SaleItem("Phones", 30, "none", "Samsung", 100)}
                    ),
                )
                notifyDataSetChanged()
            }
        }
    }
}