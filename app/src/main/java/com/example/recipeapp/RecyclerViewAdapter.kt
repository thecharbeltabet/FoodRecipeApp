package com.example.recipeapp
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

    private var recipeList = mutableListOf<Recipe>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false))
    }
    override fun getItemCount(): Int{
        return recipeList.size
    }

    fun setData(recipes:MutableList<Recipe>){
        recipeList = recipes
        notifyDataSetChanged()
    }
    fun addData(recipe:Recipe){
        recipeList.add(recipe)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int, model: Recipe) {
        holder.itemView.DishName.text = model.name.toString()
        holder.itemView.RecipeID.text = model.recipeID.toString()

        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context, "You have clicked firebase recycler view", Toast.LENGTH_SHORT).show()
        }
    }
}