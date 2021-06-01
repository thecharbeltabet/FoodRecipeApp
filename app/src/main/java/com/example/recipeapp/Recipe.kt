package com.example.recipeapp

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe (
    val name:String? = null,
    val description:String? = null,
    val ingredients:List<String>? = emptyList(),
    val recipePictureURI:String? = null
):Parcelable