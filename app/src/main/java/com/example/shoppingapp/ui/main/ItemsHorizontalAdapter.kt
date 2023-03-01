package com.example.shoppingapp.ui.main

import com.example.shoppingapp.model.base.ListItem
import com.example.shoppingapp.ui.base.BaseDiffUtilItemCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ItemsHorizontalAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()){
    init {
        delegatesManager.addDelegate(Page1Delegates.categoryItemDelegate)
            .addDelegate(Page1Delegates.categoryItemDelegate)
            .addDelegate(Page1Delegates.latestItemDelegate)
            .addDelegate(Page1Delegates.saleItemDelegate)
            .addDelegate(Page1Delegates.latestProgressDelegate)
            .addDelegate(Page1Delegates.saleProgressDelegate)

    }
}