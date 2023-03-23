package com.recipe.data.model.ingredient

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class IngredientModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: String,
) : Serializable