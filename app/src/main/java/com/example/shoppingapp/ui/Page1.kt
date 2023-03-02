package com.example.shoppingapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.example.shoppingapp.R
import com.example.shoppingapp.ui.main.Page1Delegates
import com.example.shoppingapp.databinding.ActivityPage1Binding
import com.example.shoppingapp.ui.main.Page1Adapter
import com.example.shoppingapp.viewmodel.main.MainScreenViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarMenu
import com.google.android.material.navigation.NavigationBarView
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class Page1 : AppCompatActivity() {

    private val binding by viewBinding(ActivityPage1Binding::inflate)
    private val viewModel by viewModels<MainScreenViewModel>()

    private val adapter = Page1Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            recyclerView.adapter = adapter

            viewModel.data.observe(this@Page1, Observer{
                adapter.items = it
            })
        }


        binding.navigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.action_home -> {
                    val intent = Intent(this, SignInPage::class.java)
                    startActivity(intent)
                    true
                }
//                R.id.item_2 -> {
//                    // Respond to navigation item 2 click
//                    true
//                }
                else -> false
            }
        }
    }

    private inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T) =
        lazy(LazyThreadSafetyMode.NONE) {
            bindingInflater.invoke(layoutInflater)
        }
}

