package com.example.recipeapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Recipe(
    val name: String = "",
    val description: String = "",
    val ing: String = "",
    val userEmail: String = "",
    val recipePictureURI: String = ""
):Parcelable