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
            //finish()
        }

        binding.googleLogo.setOnClickListener{
            signIn()
        }

        binding.signUpText.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }



    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            if(task.isSuccessful){
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w(TAG, "Google sign in failed", e)
                }
            }
            else{
                Log.w(TAG,task.exception.toString())
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(applicationContext, "Success!", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_LONG).show()
                }
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