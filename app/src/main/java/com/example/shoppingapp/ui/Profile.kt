package com.example.shoppingapp.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.shoppingapp.databinding.ActivityProfileBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream


class Profile : AppCompatActivity() {

    private val binding by viewBinding(ActivityProfileBinding::inflate)

    private lateinit var strBitmap : String

    private var database: FirebaseDatabase? = null
    private var usersDatabaseReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        usersDatabaseReference = database!!.reference.child("users")

        val sharedPreferences = getSharedPreferences(
            "mySharedPreferences",
            MODE_PRIVATE
        )

        sharedPreferences.getString("accountId", "")?.let { it ->
            usersDatabaseReference!!.child(it).get().addOnSuccessListener {
                if (it.exists()){
                    binding.profileNameTextView.text = it.child("name").value.toString()
                } else {
                    binding.profileNameTextView.text = "Profile name"
                }
            }
        }

        binding.uploadItemButton.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            intent.type = "image/*"
            loadImageActivityResultLauncher.launch(intent)
        }

        val sharedPreferencesPhoto = getSharedPreferences(
            "photoSharedPreferences",
            MODE_PRIVATE
        )
        strBitmap = sharedPreferencesPhoto.getString("accountPhoto", null).toString()
        binding.profileImageView.setImageBitmap(decodeBase64(strBitmap))

        binding.navigation.selectedItemId = com.example.shoppingapp.R.id.action_profile
        binding.navigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                com.example.shoppingapp.R.id.action_home -> {
                    intent = Intent(this, Page1::class.java)
                    startActivity(intent)
                    true
                }
                com.example.shoppingapp.R.id.action_profile -> {
                    true
                }
                com.example.shoppingapp.R.id.action_favorite -> {
                    Toast.makeText(this, "Favorites", Toast.LENGTH_SHORT).show()
                    true
                }
                com.example.shoppingapp.R.id.action_chat -> {
                    Toast.makeText(this, "Chat", Toast.LENGTH_SHORT).show()
                    true
                }
                com.example.shoppingapp.R.id.action_shopping_cart -> {
                    Toast.makeText(this, "Shopping cart", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        binding.logOutLinearLayout.setOnClickListener { v ->
            if (v != null) {
                intent = Intent(v.context, SignInPage::class.java)
            }
            v?.context?.startActivity(intent)
        }
    }

   private var loadImageActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            try {
                val data = result.data
                val imageUri: Uri? = data?.data
                val imageStream: InputStream? = imageUri?.let { contentResolver.openInputStream(it) }
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                val sharedPreferencesPhoto = getSharedPreferences(
                    "photoSharedPreferences",
                    MODE_PRIVATE
                )
                val editor = sharedPreferencesPhoto.edit()
                editor.putString("accountPhoto", bitmapToString(selectedImage))
                editor.apply()
                strBitmap = sharedPreferencesPhoto.getString("accountPhoto", null).toString()
                binding.profileImageView.setImageBitmap(decodeBase64(strBitmap))
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show()
        }
    }

    private fun bitmapToString(bitmap: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val arr: ByteArray = baos.toByteArray()
        return Base64.encodeToString(arr, Base64.DEFAULT)
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