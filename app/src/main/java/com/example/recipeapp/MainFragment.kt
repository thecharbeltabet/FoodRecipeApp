package com.example.recipeapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.FirebaseApiNotAvailableException
import com.google.firebase.auth.FirebaseAuth
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

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.main_fragment_overflow, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.aboutUsItem){
            findNavController().navigate(R.id.action_mainFragment_to_aboutUsFragment)
        }else if(item.itemId == R.id.signOutItem){
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(activity, SignInActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}