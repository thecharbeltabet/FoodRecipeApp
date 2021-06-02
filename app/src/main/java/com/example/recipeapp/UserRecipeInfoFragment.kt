package com.example.recipeapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_recipe_info.view.*

class UserRecipeInfoFragment : Fragment() {
    private val args by navArgs<UserRecipeInfoFragmentArgs>()
    private lateinit var recipeViewModel: RecipeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipe_info, container, false)
        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        view.recipeName.setText(args.recipe.name)
        view.recipeDescription.setText(args.recipe.description)

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteOverflow){
            val alertBuilder = AlertDialog.Builder(requireContext())
            alertBuilder.setPositiveButton("Yes"){_,_ ->
                recipeViewModel.deleteRecipe(args.recipe, args.recipeID)
                findNavController().navigate(R.id.action_userRecipeInfoFragment_to_userRecipesFragment)
            }
            alertBuilder.setNegativeButton("No"){_,_ ->}
            alertBuilder.setTitle("Delete ${args.recipe.name}?")// using string template
            alertBuilder.setMessage(("Sure Delete ${args.recipe.description}?"))
            alertBuilder.create().show()
        }
        return super.onOptionsItemSelected(item)
    }
}