package com.example.recipeapp

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class FirebaseRepo {

    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getPostList(): Task<QuerySnapshot> {
        return firebaseFirestore
            .collection("recipes")
            .orderBy("name", Query.Direction.DESCENDING)
            .get()
    }
}