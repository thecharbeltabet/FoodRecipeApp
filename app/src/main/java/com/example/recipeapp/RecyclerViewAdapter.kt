package com.example.recipeapp
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import androidx.navigation.findNavController
import com.google.firebase.firestore.DocumentSnapshot

class RecyclerViewAdapter(options: FirestoreRecyclerOptions<Recipe>): FirestoreRecyclerAdapter<Recipe, RecyclerViewAdapter.ViewHolder>(options){

    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Recipe) {
        // Populate the row with the data from the noteList of index position

        // Set the title for the recyclerView row
        holder.itemView.DishName.text = model.name.toString()
        holder.itemView.RecipeID.text = model.description.toString()
    }
}