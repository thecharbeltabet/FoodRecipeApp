package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth

@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler()

        handler.postDelayed({
            if (FirebaseAuth.getInstance().currentUser != null){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }
            else{
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }
        }, 3000)
    }
}