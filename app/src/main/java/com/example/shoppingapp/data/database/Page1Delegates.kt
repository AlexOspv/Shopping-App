package com.example.shoppingapp.data.database

import com.bumptech.glide.Glide
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.CategoryItemBinding
import com.example.shoppingapp.databinding.HorizontalListItemBinding
import com.example.shoppingapp.databinding.LatestItemBinding
import com.example.shoppingapp.databinding.SaleItemBinding
import com.example.shoppingapp.view.*
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object Page1Delegates {

    val itemsHorizontalDelegate = adapterDelegateViewBinding<ItemsHorizontalItem, ListItem, HorizontalListItemBinding>(
        { layoutInflater, root ->
            HorizontalListItemBinding.inflate(layoutInflater, root, false).apply {
                recyclerView.adapter = ListDelegationAdapter(
                    categoryItemDelegate,
                    latestItemDelegate,
                    saleItemDelegate
                )
            }
        }
    ) {
        bind {
            binding.categoryName = item.title
            binding.viewAll = item.viewAllButton
            (binding.recyclerView.adapter as ListDelegationAdapter<List<ListItem>>).apply {
                items = item.list
                notifyDataSetChanged()
            }
        }
    }

    private val categoryItemDelegate =
        adapterDelegateViewBinding<CategoryItem, ListItem, CategoryItemBinding>(
            { layoutInflater, root -> CategoryItemBinding.inflate(layoutInflater, root, false)
    }
    ) {
        bind {
                binding.categoryNameTextView.text = item.name
                binding.categoryIconImageView.setImageResource(R.drawable.robot_icon)
        }
    }

    private val latestItemDelegate =
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

    private val saleItemDelegate =
        adapterDelegateViewBinding<SaleItem, ListItem, SaleItemBinding>({ inflater, container ->
            SaleItemBinding.inflate(inflater, container, false)
        }
        ) {
            bind {
                binding.saleItemImageView.setImageResource(R.drawable.apple_icon)
                binding.category = item.category
                binding.name = item.name
                binding.price = "$ " +item.price.toString()
                binding.executePendingBindings()
            }
        }

//    private val horizontalItemAdapter = ListDelegationAdapter(
//        categoryItemDelegate,
//        latestItemDelegate,
//        saleItemDelegate
//    )
}