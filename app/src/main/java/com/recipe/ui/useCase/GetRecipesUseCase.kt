package com.recipe.ui.useCase

import com.recipe.data.model.recipe.RecipeResponse
import com.recipe.di.UseCaseDispatcher
import com.recipe.repository.RecipeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository,
    @UseCaseDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Response<RecipeResponse> =
        withContext(dispatcher) {
            return@withContext recipeRepository.getRecipes()
        }
}