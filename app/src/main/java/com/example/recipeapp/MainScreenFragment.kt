package com.example.recipeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.recipeapp.databinding.FragmentMainScreenBinding

class MainScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentMainScreenBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_screen, container, false)
        binding.textView3.text = "Hello ${Globalvars.name} "


        return binding.root
    }
}