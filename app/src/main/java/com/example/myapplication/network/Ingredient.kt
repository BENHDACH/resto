package com.example.myapplication.network

import com.google.gson.annotations.SerializedName

class Ingredient(
    @SerializedName("name_fr") val ingredientN: String//Le nom d'un ingredient
) {
}