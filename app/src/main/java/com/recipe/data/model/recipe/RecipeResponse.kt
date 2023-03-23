package com.recipe.data.model.recipe

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecipeResponse(
   @SerializedName("recipes")
    val results: List<RecipeModel>
) : Serializable