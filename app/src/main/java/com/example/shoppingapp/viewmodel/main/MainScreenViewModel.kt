package com.example.shoppingapp.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.model.base.ListItem
import com.example.shoppingapp.model.items.*
import com.example.shoppingapp.viewmodel.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainScreenViewModel : BaseViewModel() {

    private val _data = MutableLiveData<List<ListItem>>()
    val data: LiveData<List<ListItem>> = _data

    init {
        viewModelScope.launch(Dispatchers.IO){
            _data.postValue(getLoaders())
            val items = getItems()
            _data.postValue(items)
        }
    }

    private fun getLoaders(): List<ListItem> {
        return listOf(
            ItemsHorizontalItem(
                title = " ",
                viewAllButton = " ",
                list = IntRange(1, 20).map { CategoryItem("Phones", 1) }
            ),
            ItemsHorizontalItem(
                title = "Latest",
                viewAllButton = "View all",
                list = IntRange(1, 4).map { ProgressLatestItem }
            ),
            ItemsHorizontalItem(
                title = "Sale",
                viewAllButton = "View all",
                list = IntRange(1, 3).map { ProgressSaleItem }
            )
        )
    }

    private suspend fun getItems(): List<ListItem> {
        delay(2000L)
        return listOf(
            ItemsHorizontalItem(
                title = " ",
                viewAllButton = " ",
                list = IntRange(1, 20).map { CategoryItem("Phones", 1) }
            ),
            ItemsHorizontalItem(
                title = "Latest",
                viewAllButton = "View all",
                list = IntRange(1, 20).map { LatestItem("Phones", "none", "Samsung", 100) }
            ),
            ItemsHorizontalItem(
                title = "Sale",
                viewAllButton = "View all",
                list = IntRange(1, 20).map { SaleItem("Phones", 30, "none", "Samsung", 100) }
            )
        )
    }

}

