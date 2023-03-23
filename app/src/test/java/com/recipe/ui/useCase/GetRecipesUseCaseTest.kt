package com.recipe.ui.useCase

import com.recipe.createRecipeResponse
import com.recipe.repository.RecipeRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class GetRecipesUseCaseTest {

    @MockK
    lateinit var repository: RecipeRepository

    private val dispatcher = StandardTestDispatcher()

    private lateinit var getRecipesUseCase: GetRecipesUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getRecipesUseCase = GetRecipesUseCase(repository, dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke should return a Response with RecipeResponse`() {
        runTest(dispatcher) {

            coEvery { repository.getRecipes() } returns createRecipeResponse()

            val getRecipesUseCase = getRecipesUseCase.invoke()

            assertNotNull(getRecipesUseCase)

        }

    }
}