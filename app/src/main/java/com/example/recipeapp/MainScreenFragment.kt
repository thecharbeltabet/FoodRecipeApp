package com.example.recipeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.databinding.FragmentMainScreenBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_main_screen.view.*

class MainScreenFragment : Fragment() {
    private lateinit var recipeViewModel: RecipeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_screen, container, false)
        val query = FirebaseFirestore.getInstance().collection("recipes").orderBy("name")
        val options =  FirestoreRecyclerOptions.Builder<Recipe>().setQuery(query,Recipe::class.java).build()
        val adapter = RecyclerViewAdapter(options)
        val recyclerView = view.recyclerView
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.startListening()

        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        setHasOptionsMenu(true)

        return view
    }
}