package com.example.recipeapp

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_new_recipe.view.*
import kotlinx.android.synthetic.main.fragment_new_recipe.*

class NewRecipeFragment : Fragment() {
    private lateinit var recipeViewModel: RecipeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_new_recipe, container, false)

        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        view.addButton.setOnClickListener{
            insertToDatabase()
        }

        return view
    }


    private fun insertToDatabase(){

        lateinit var userEmail: String
        Firebase.auth.currentUser?.let {
            for (profile in it.providerData){
                userEmail = profile.uid
            }
        }
        val name = recipeNameInput.text.toString()
        val description = recipeDescriptionInput.text.toString()
        val ingredients = recipeIngredientsInput.text.toString()

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(description) && !TextUtils.isEmpty(ingredients)){
            val recipe = Recipe(name, description, ingredients, userEmail)
            recipeViewModel.addRecipe(recipe)
            findNavController().navigate(R.id.action_newRecipeFragment_to_userRecipesFragment)
        }else{
            Toast.makeText(requireContext(), "Blank fields are not allowed!", Toast.LENGTH_SHORT).show()
        }
    }
}