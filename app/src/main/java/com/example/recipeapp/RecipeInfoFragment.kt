package com.example.recipeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import coil.load
import kotlinx.android.synthetic.main.fragment_recipe_info.view.*

class RecipeInfoFragment : Fragment() {

    private val args by navArgs<RecipeInfoFragmentArgs>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipe_info, container, false)

        view.recipeName.text = args.recipe.name
        view.recipeDescription.text = args.recipe.description
        view.ingredientsText.text = args.recipe.ing
        view.recipeInfoImage.load(args.recipe.recipePictureURI)

        return view
    }

}