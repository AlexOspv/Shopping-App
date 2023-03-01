package com.example.shoppingapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.example.shoppingapp.ui.main.Page1Delegates
import com.example.shoppingapp.databinding.ActivityPage1Binding
import com.example.shoppingapp.ui.main.Page1Adapter
import com.example.shoppingapp.viewmodel.main.MainScreenViewModel
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
    }

    private inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T) =
        lazy(LazyThreadSafetyMode.NONE) {
            bindingInflater.invoke(layoutInflater)
        }
}

