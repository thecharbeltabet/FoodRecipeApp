package com.example.recipeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_recipe_info.view.*

class RecipeInfoFragment : Fragment() {

    private val args by navArgs<RecipeInfoFragmentArgs>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipe_info, container, false)

        view.recipeName.setText(args.recipe.name)
        view.recipeDescription.setText(args.recipe.description)

        return view
    }

}