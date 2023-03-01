package com.example.shoppingapp.ui.main

import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.CategoryItemBinding
import com.example.shoppingapp.databinding.HorizontalListItemBinding
import com.example.shoppingapp.databinding.ItemProgressLatestBinding
import com.example.shoppingapp.databinding.ItemProgressSaleBinding
import com.example.shoppingapp.databinding.LatestItemBinding
import com.example.shoppingapp.databinding.SaleItemBinding
import com.example.shoppingapp.model.base.ListItem
import com.example.shoppingapp.model.items.*
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object Page1Delegates {

    val itemsHorizontalDelegate = adapterDelegateViewBinding<ItemsHorizontalItem, ListItem, HorizontalListItemBinding>(
        { layoutInflater, root ->
            HorizontalListItemBinding.inflate(layoutInflater, root, false)
        }
    ) {
        //onCreateViewHolder
        val adapter = ItemsHorizontalAdapter()
        binding.recyclerView.adapter = adapter


        //onBindViewHolder
        bind {
            binding.categoryName = item.title
            binding.viewAll = item.viewAllButton
            adapter.items = item.list
        }

        //onViewRecycled
        onViewRecycled {
            //...
        }
    }

    val categoryItemDelegate =
        adapterDelegateViewBinding<CategoryItem, ListItem, CategoryItemBinding>(
            { layoutInflater, root -> CategoryItemBinding.inflate(layoutInflater, root, false)
    }
    ) {
        bind {
                binding.categoryNameTextView.text = item.name
                binding.categoryIconImageView.setImageResource(R.drawable.robot_icon)
        }
    }

    val latestItemDelegate =
        adapterDelegateViewBinding<LatestItem, ListItem, LatestItemBinding>(
            {layoutInflater, root -> LatestItemBinding.inflate(layoutInflater, root, false)}
    ) {
            bind {
                binding.latestItemImageView.setImageResource(R.drawable.google_icon)
                binding.category = item.category
                binding.name = item.name
                binding.price = "$ " +item.price.toString()
                binding.executePendingBindings()
        }
    }

    val latestProgressDelegate =
        adapterDelegateViewBinding<ProgressLatestItem, ListItem, ItemProgressLatestBinding>(
            { layoutInflater, root -> ItemProgressLatestBinding.inflate(layoutInflater, root, false)
            }
        ) {}

    val saleItemDelegate =
        adapterDelegateViewBinding<SaleItem, ListItem, SaleItemBinding>(
            { inflater, container -> SaleItemBinding.inflate(inflater, container, false) }
        ) {
            bind {
                binding.saleItemImageView.setImageResource(R.drawable.apple_icon)
                binding.category = item.category
                binding.name = item.name
                binding.price = "$ " +item.price.toString()
                binding.executePendingBindings()
            }
        }

    val saleProgressDelegate =
        adapterDelegateViewBinding<ProgressSaleItem, ListItem, ItemProgressSaleBinding>(
            { layoutInflater, root -> ItemProgressSaleBinding.inflate(layoutInflater, root, false)
            }
        ) {}
}