package com.example.recipeapp

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val firebaseRepo: FirebaseRepo = FirebaseRepo()
    private var recipeList: List<Recipe> = ArrayList()
    private val recipeListAdapter : RecipeListAdapter = RecipeListAdapter(recipeList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadPostData()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recipeListAdapter
    }

    private fun loadPostData(){
        firebaseRepo.getPostList().addOnCompleteListener{
            if(it.isSuccessful){
                recipeList = it.result!!.toObjects(Recipe::class.java)
                recipeListAdapter.recipeListItems = recipeList
                recipeListAdapter.notifyDataSetChanged()
            }
        }
    }
}