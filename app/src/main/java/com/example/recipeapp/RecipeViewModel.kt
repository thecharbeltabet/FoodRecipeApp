package com.example.recipeapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application): AndroidViewModel(application) {
    val readAllRecipes: LiveData<List<Recipe>>

    private val repository: FirebaseRecipeRepository = FirebaseRecipeRepository()

    init {
        readAllRecipes = repository.readAllRecipes
    }

    fun addRecipe(recipe:Recipe){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRecipe(recipe)
        }
    }

    fun updateRecipe(recipe: Recipe, id:String){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateRecipe(recipe, id)
        }
    }
    fun deleteNote(recipe:Recipe,id:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRecipe(recipe, id)
        }
    }
    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}
