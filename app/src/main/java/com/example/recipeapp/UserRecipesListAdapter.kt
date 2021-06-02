package com.example.recipeapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.android.synthetic.main.item_layout.view.*

class UserRecipesListAdapter(options: FirestoreRecyclerOptions<Recipe>): FirestoreRecyclerAdapter<Recipe, UserRecipesListAdapter.ViewHolder>(options) {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Recipe) {
        // Populate the row with the data from the noteList of index position

        // Set the title for the recyclerView row
        holder.itemView.recipeName.text = model.name.toString()
        holder.itemView.recipeDiscription.text = model.description.toString()

        holder.itemView.setOnClickListener(){
            val snapshot: DocumentSnapshot = snapshots.getSnapshot(position)
            Log.i("message", "snapshotID ${snapshot.id.toString()}")

            val action = UserRecipesFragmentDirections.actionUserRecipesFragmentToUserRecipeInfoFragment(model, snapshot.id)
            holder.itemView.findNavController().navigate(action)
        }
    }
}