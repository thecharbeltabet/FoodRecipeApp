package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.recipeapp.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        binding.logInButton.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.signUpText.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}