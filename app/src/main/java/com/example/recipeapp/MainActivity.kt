package com.example.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val adapter = RecyclerViewAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter


        populateCards(adapter)

        binding.floatingAddButton.setOnClickListener{
            adapter.addData(Recipe("Bruger", 1212, ""))
        }
    }

    private fun populateCards(adapter:RecyclerViewAdapter) {
        val recipeList: MutableList<Recipe> = mutableListOf()
        for(i in 1..10){
            recipeList.add(Recipe("Burger", i, ""))
        }
        adapter.setData(recipeList.toMutableList())
    }
}