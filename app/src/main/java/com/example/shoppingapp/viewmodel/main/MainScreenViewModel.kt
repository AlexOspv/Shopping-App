package com.example.shoppingapp.viewmodel.main

import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.R
import com.example.shoppingapp.core.network.di.NetworkComponent
import com.example.shoppingapp.model.base.ListItem
import com.example.shoppingapp.model.items.*
import com.example.shoppingapp.viewmodel.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainScreenViewModel : BaseViewModel() {

    private val api = NetworkComponent.createApi()
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
                list = IntRange(1, 20).map { CategoryItem("Phones", R.drawable.add_to_cart_sheet) }
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
        val categoryListItem = listOf(
            CategoryItem("Pones",  com.example.shoppingapp.R.drawable.phone_icon),
            CategoryItem("Headphones", image = R.drawable.headphones_icon),
            CategoryItem("Games", image = R.drawable.controller_icon),
            CategoryItem("Cars", image = R.drawable.car_icon),
            CategoryItem("Furniture", image = R.drawable.bed_icon),
            CategoryItem("Kids", image = R.drawable.robot_icon),
        )
        val latestResponse = api.latestItems()
        val latestListItem = latestResponse.latest.map {
            LatestItem(
                name = it.name,
                category = it.category,
                price = it.price,
                image_url = it.image_url
            )
        }
        val saleResponse = api.saleItems()
        val saleListItem = saleResponse.sale.map {
            SaleItem(
                name = it.name,
                category = it.category,
                price = it.price,
                image_url = it.image_url,
                discount = it.discount
            )
        }

        return listOf(
            ItemsHorizontalItem(
                title = " ",
                viewAllButton = " ",
                list = categoryListItem
            ),
            ItemsHorizontalItem(
                title = "Latest",
                list = latestListItem
            ),
            ItemsHorizontalItem(
                title = "Flash sale",
                list = saleListItem
            )
        )
    }
}

