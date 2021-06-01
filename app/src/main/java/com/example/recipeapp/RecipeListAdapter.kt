package com.example.recipeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class RecipeListAdapter(var recipeListItems: List<Recipe>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class RecipeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(recipe: Recipe){
            itemView.recipeName.text = recipe.name
            itemView.recipeDiscription.text = recipe.description
            itemView.recipeImage.setImageURI(recipe.recipePictureURI.toUri())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RecipeViewHolder).bind(recipeListItems[position])
    }

    override fun getItemCount(): Int {
        return recipeListItems.size
    }

}