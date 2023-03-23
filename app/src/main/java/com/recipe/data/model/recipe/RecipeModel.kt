package com.recipe.data.model.recipe

import com.google.gson.annotations.SerializedName
import com.recipe.data.model.ingredient.IngredientModel
import java.io.Serializable

data class RecipeModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("method_preparation")
    val methodPreparation: String,
    @SerializedName("image")
    val image:String,
    @SerializedName("origin_latitude")
    val originLatitude:Double,
    @SerializedName("origin_longitude")
    val originLongitude:Double,
    @SerializedName("country")
    val country:String,
    @SerializedName("ingredients")
    val ingredients: List<IngredientModel>
) : Serializable