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
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {
    private lateinit var recipeViewModel: RecipeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val query = FirebaseFirestore.getInstance().collection("recipes").whereEqualTo("userEmail","null").orderBy("name")
        val options = FirestoreRecyclerOptions.Builder<Recipe>().setQuery(query, Recipe::class.java).build()
        val adapter = RecipesListAdapter(options)
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.startListening()

        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        view.myRecipesButton.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_userRecipesFragment)
        }

        return view
    }
}