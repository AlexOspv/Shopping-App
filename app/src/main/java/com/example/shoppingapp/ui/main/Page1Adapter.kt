package com.example.shoppingapp.ui.main

import com.example.shoppingapp.model.base.ListItem
import com.example.shoppingapp.ui.base.BaseDiffUtilItemCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class Page1Adapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()){
    init {
        delegatesManager.addDelegate(Page1Delegates.itemsHorizontalDelegate)
    }
}