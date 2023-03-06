package com.example.shoppingapp.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ActivityPage1Binding
import com.example.shoppingapp.ui.main.Page1Adapter
import com.example.shoppingapp.viewmodel.main.MainScreenViewModel


class Page1 : AppCompatActivity() {

    private val binding by viewBinding(ActivityPage1Binding::inflate)
    private val viewModel by viewModels<MainScreenViewModel>()

    private lateinit var strBitmap : String

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

        val sharedPreferences = getSharedPreferences(
            "photoSharedPreferences",
            MODE_PRIVATE
        )

        strBitmap = sharedPreferences.getString("accountPhoto", null).toString()
        binding.profileImageView.setImageBitmap(decodeBase64(strBitmap))

        binding.navigation.selectedItemId = R.id.action_home
        binding.navigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.action_home -> {
                    true
                }
                R.id.action_profile -> {
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)
                    true
                }
                R.id.action_favorite -> {
                    Toast.makeText(this, "Favorites", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_chat -> {
                    Toast.makeText(this, "Chat", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_shopping_cart -> {
                    Toast.makeText(this, "Shopping cart", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun decodeBase64(input: String?): Bitmap? {
        val decodedByte: ByteArray = Base64.decode(input, 0)
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
    }

    private inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T) =
        lazy(LazyThreadSafetyMode.NONE) {
            bindingInflater.invoke(layoutInflater)
        }
}

