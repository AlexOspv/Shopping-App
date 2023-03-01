package com.example.shoppingapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppingapp.model.items.User
import com.example.shoppingapp.databinding.ActivitySignInPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

const val TAG = "SignInActivity"

class SignInPage : AppCompatActivity() {

    private lateinit var binding: ActivitySignInPageBinding

    private lateinit var auth: FirebaseAuth

    private var loginModeActive = false

    private var database: FirebaseDatabase? = null
    private var usersDatabaseReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        usersDatabaseReference = database!!.reference.child("users")

        binding.signUpLogInButton.setOnClickListener(View.OnClickListener {
            loginSignUpUser(binding.emailEditText.text.toString().trim { it <= ' ' },
                binding.passwordEditText.text.toString().trim { it <= ' ' })
        })
    }

    private fun loginSignUpUser(email: String, password: String) {

        if (loginModeActive) {
            if (binding.passwordEditText.text.toString().trim { it <= ' ' }.length < 6) {
                Toast.makeText(this, "Password must be at lest 6 characters", Toast.LENGTH_SHORT)
                    .show()
            } else if (binding.emailEditText.text.toString().trim { it <= ' ' } == "") {
                Toast.makeText(this, "Please input your email", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                        this
                    ) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(
                                TAG,
                                "signInWithEmail:success"
                            )
                            val user = auth.currentUser
                            //updateUI(user);
                            val intent = Intent(this, Page1::class.java)
                            intent.putExtra(
                                "userName",
                                binding.nameEditText.text.toString().trim { it <= ' ' })
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(
                                TAG,
                                "signInWithEmail:failure",
                                task.exception
                            )
                            Toast.makeText(
                                this, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            //updateUI(null);
                        }
                    }
            }
        } else {
            if (binding.passwordEditText.text.toString().trim { it <= ' ' }.length < 6) {
                Toast.makeText(this, "Password must be at lest 6 characters", Toast.LENGTH_SHORT)
                    .show()
            } else if (binding.emailEditText.text.toString().trim { it <= ' ' } == "") {
                Toast.makeText(this, "Please input your email", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                        this
                    ) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(
                                TAG,
                                "createUserWithEmail:success"
                            )
                            val user = auth.currentUser

                            if (user != null) {
                                createUser(user)
                            }

                            //updateUI(user);
                            val intent = Intent(this, Page1::class.java)
                            intent.putExtra(
                                "userName",
                                binding.nameEditText.text.toString().trim { it <= ' ' })
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(
                                TAG,
                                "createUserWithEmail:failure",
                                task.exception
                            )
                            Toast.makeText(
                                this, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            //updateUI(null);
                        }
                    }
            }
        }

    }

    private fun createUser(firebaseUser: FirebaseUser) {
        val user = User()
        user.id = firebaseUser.uid
        user.email = firebaseUser.email
        user.name = binding.nameEditText.text.toString().trim { it <= ' ' }
        usersDatabaseReference?.push()?.setValue(user)
    }

    fun toggleLogInMode(view: View?) {
        if (loginModeActive) {
            loginModeActive = false
            binding.signInWelcomeBackTextView.text = "Sign in"
            binding.signUpLogInButton.text = "Sign In"
            binding.hintTextView.text = "Already have an account?"
            binding.toggleLoginSignUpTextView.text = "Log in"
            binding.nameTextField.visibility = View.VISIBLE
            binding.googleLayout.visibility = View.VISIBLE
            binding.appleLayout.visibility = View.VISIBLE
        } else {
            loginModeActive = true
            binding.signInWelcomeBackTextView.text = "Welcome back"
            binding.signUpLogInButton.text = "Login"
            binding.hintTextView.text = "Don't have an account?"
            binding.toggleLoginSignUpTextView.text = "Sign in"
            binding.nameTextField.visibility = View.GONE
            binding.googleLayout.visibility = View.GONE
            binding.appleLayout.visibility = View.GONE
        }
    }
}