package com.example.recipeapp

import android.app.Application

class Globalvars: Application() {
    companion object {
        @JvmField
        var name: String = "Foodie"
    }
}