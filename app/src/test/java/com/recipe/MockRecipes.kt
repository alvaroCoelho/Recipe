package com.recipe

import com.recipe.data.model.recipe.RecipeModel
import com.recipe.data.model.recipe.RecipeResponse
import retrofit2.Response


 fun createRecipeResponse(): Response<RecipeResponse> {
    val listRecipes = arrayListOf(
        RecipeModel("Recipe1","descriptio1","methodPreparation1",
            "Image1",1.1,1.1,"country1", emptyList() ),
        RecipeModel("Recipe2","descriptio2","methodPreparation2",
            "Image2",2.2,2.2,"country2", emptyList() ),
        RecipeModel("Recipe3","descriptio3","methodPreparation3",
            "Image3",3.3,3.3,"country3", emptyList() ),
    )
    val recipeResponse = RecipeResponse(listRecipes)
    return Response.success(recipeResponse)
}