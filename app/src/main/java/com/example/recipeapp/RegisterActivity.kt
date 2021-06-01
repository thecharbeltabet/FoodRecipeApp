package com.example.recipeapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.TypedValue
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.example.recipeapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

private lateinit var firebaseAuth: FirebaseAuth


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            var typedValue: TypedValue = TypedValue()
            theme.resolveAttribute(R.attr.colorPrimaryVariant,typedValue,true)
            var themeColor: Int = typedValue.data
            window.statusBarColor = themeColor
        }


        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.createAccountButton.setOnClickListener{
            register()

        }
    }





    private fun register(){
        if(!TextUtils.isEmpty(binding.emailInput.text.toString()) && !TextUtils.isEmpty(binding.passwordInput.text.toString()))
            firebaseAuth.createUserWithEmailAndPassword(binding.emailInput.text.toString().trim(),binding.passwordInput.text.toString().trim())
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        finish()
                    }
                }
    }
}