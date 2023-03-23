package com.recipe.repository

import com.recipe.data.remote.ServiceAPI
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val api: ServiceAPI
) {
    suspend fun getRecipes() = api.listAllRecipe()
}