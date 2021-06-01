package com.example.recipeapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.WriteBatch

class FirebaseRecipeRepository() {
    private val firestoreDB = FirebaseFirestore.getInstance()

    val readAllRecipes: LiveData<List<Recipe>> = readAllRecipes()

    fun readAllRecipes():LiveData<List<Recipe>> {
        var recipes = MutableLiveData<List<Recipe>>()
        firestoreDB.collection("notes")
            .orderBy("title")
            .addSnapshotListener{ snapshot, e ->
                if (e != null) {
                    Log.i("message", "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    var snapshotList:List<DocumentSnapshot> = snapshot.documents
                    var notesList : MutableList<Recipe> = mutableListOf()
                    for(snapshot: DocumentSnapshot in snapshotList){
                        notesList.add(snapshot.toObject(Recipe::class.java)!!)
                    }
                    recipes.value = notesList
                    Log.i("message", "Current data: ${snapshot.documents}")
                } else {
                    Log.i("message", "Current data: null")
                }
            }
        return recipes
    }

    suspend fun addRecipe(recipe: Recipe){
        firestoreDB
            .collection("recipes")
            .add(recipe)
            .addOnSuccessListener {
                Log.i("message","Successfully added Note")
            }
            .addOnFailureListener{
                Log.i("message","Failed to add Note")
            }
    }

    suspend fun updateRecipe(recipe: Recipe,id:String){
        firestoreDB.collection("recipes")
            .document(id)
            .update("name",recipe.name,
                "description",recipe.description)
            .addOnSuccessListener {
                Log.i("message","Successfully updated Note")
            }.addOnFailureListener{
                Log.i("message","Failed to update Note")
            }
    }

    suspend fun deleteRecipe(recipe:Recipe,id: String){
        firestoreDB.collection("recipes")
            .document(id)
            .delete()
            .addOnSuccessListener {
                Log.i("message","Successfully deleted Note")
            }.addOnFailureListener{
                Log.i("message","Failed to delete Note")
            }
    }

    suspend fun deleteAll()
    {
        firestoreDB.collection("recipes")
            .get()
            .addOnSuccessListener {
                val batch: WriteBatch = FirebaseFirestore.getInstance().batch()
                val snapshotList: List<DocumentSnapshot> = it.documents
                for(snapShot:DocumentSnapshot in snapshotList){
                    batch.delete(snapShot.reference)
                }
                batch.commit().addOnSuccessListener {
                    Log.i("message","Successfully all Note")
                }.addOnFailureListener{
                    Log.i("message","Failed to delete Notes")
                }
            }.addOnFailureListener{
                Log.i("message","Failed to get Notes")
            }
    }
}