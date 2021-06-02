package com.example.recipeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_user_recipes.view.*

class UserRecipesFragment : Fragment() {
    private lateinit var recipeViewModel: RecipeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_recipes, container, false)

        var user = getEmail()

        val query = FirebaseFirestore.getInstance().collection("recipes").orderBy("name").whereEqualTo("userEmail",user)
        val options = FirestoreRecyclerOptions.Builder<Recipe>().setQuery(query, Recipe::class.java).build()
        val adapter = UserRecipesListAdapter(options)
        val recyclerView = view.recyclerViewUser
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.startListening()

        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_userRecipesFragment_to_newRecipeFragment)
        }

        return view
    }
    private fun getEmail(): String {

        val user = Firebase.auth.currentUser
        var uid = ""
        user?.let {
            for (profile in it.providerData) {

                uid = profile.uid.toString()


            }}
        return uid.toString()
    }
}