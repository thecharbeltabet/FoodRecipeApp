package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.recipeapp.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider



@Suppress("DEPRECATION")
class SignInActivity : AppCompatActivity() {

    companion object{
        private const val  RC_SIGN_IN = 999
    }

    private val TAG:String = "SignInActivity"
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("553494778829-n12s2qdbb4maarpgnitsoive7d2oit9c.apps.googleusercontent.com")//The ID from json services file
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.logInButton.setOnClickListener{
            emailSignIn()
        }

        binding.signUpText.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun emailSignIn() {
        if (!TextUtils.isEmpty(binding.emailInput.text.toString()) && !TextUtils.isEmpty(binding.passwordInput.text.toString())) {
            firebaseAuth.signInWithEmailAndPassword(
                binding.emailInput.text.toString(),
                binding.passwordInput.text.toString()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(applicationContext, "Successfully signed in!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Check your email and password or register!", Toast.LENGTH_LONG).show()

                    }
                }
        } else
        {
            Toast.makeText(applicationContext, "Enter your credentials!", Toast.LENGTH_LONG).show()
        }
    }
}