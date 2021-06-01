package com.example.recipeapp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter:RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    private var recipeList = emptyList<Recipe>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.eddy_item_layout,parent,false))
    }
    override fun getItemCount(): Int{
        return recipeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = recipeList[position]
    }

    fun setData(recipes:List<Recipe>){
        recipeList = recipes
        notifyDataSetChanged()
    }
}