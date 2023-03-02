package com.example.shoppingapp.ui.main

import android.util.Log
import com.bumptech.glide.Glide
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.CategoryItemBinding
import com.example.shoppingapp.databinding.HorizontalListItemBinding
import com.example.shoppingapp.databinding.ItemProgressLatestBinding
import com.example.shoppingapp.databinding.ItemProgressSaleBinding
import com.example.shoppingapp.databinding.LatestItemBinding
import com.example.shoppingapp.databinding.SaleItemBinding
import com.example.shoppingapp.model.base.ListItem
import com.example.shoppingapp.model.items.*
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import kotlin.reflect.typeOf

object Page1Delegates {

    val itemsHorizontalDelegate = adapterDelegateViewBinding<ItemsHorizontalItem, ListItem, HorizontalListItemBinding>(
        { layoutInflater, root ->
            HorizontalListItemBinding.inflate(layoutInflater, root, false)
        }
    ) {
        val adapter = ItemsHorizontalAdapter()
        binding.recyclerView.adapter = adapter

        bind {
            binding.categoryName = item.title
            binding.viewAll = item.viewAllButton
            adapter.items = item.list
        }

    }

    val categoryItemDelegate =
        adapterDelegateViewBinding<CategoryItem, ListItem, CategoryItemBinding>(
            { layoutInflater, root -> CategoryItemBinding.inflate(layoutInflater, root, false)
            }
    ) {
        bind {
            binding.category = item.name
            binding.categoryIconImageView.setImageResource(item.image)
            binding.executePendingBindings()
        }
    }

    val latestItemDelegate =
        adapterDelegateViewBinding<LatestItem, ListItem, LatestItemBinding>(
            {layoutInflater, root -> LatestItemBinding.inflate(layoutInflater, root, false)
            }
    ) {
            bind {
                binding.category = item.category
                binding.name = item.name
                binding.price = "$ " + item.price.toString().format("%,3f").replace(".",",")
                Glide.with(binding.latestItemImageView).load(item.image_url).into(binding.latestItemImageView)
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
                binding.category = item.category
                binding.name = item.name
                binding.saleAmount = item.discount.toString() + "% off"
                binding.price = "$ " +item.price.toString().format("%,2f").replace(".",",")
                Glide.with(binding.saleItemImageView).load(item.image_url).into(binding.saleItemImageView)
                binding.executePendingBindings()
            }
        }

    val saleProgressDelegate =
        adapterDelegateViewBinding<ProgressSaleItem, ListItem, ItemProgressSaleBinding>(
            { layoutInflater, root -> ItemProgressSaleBinding.inflate(layoutInflater, root, false)
            }
        ) {}
}