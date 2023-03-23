package com.recipe.data.remote

import com.recipe.data.model.recipe.RecipeModel
import com.recipe.data.model.recipe.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServiceAPI {

@GET("recipe")
suspend fun listAllRecipe():Response<RecipeResponse>
}