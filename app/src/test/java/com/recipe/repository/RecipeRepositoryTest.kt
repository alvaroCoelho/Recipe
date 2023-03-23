package com.recipe.repository


import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.recipe.data.model.recipe.RecipeResponse
import com.recipe.data.remote.ServiceAPI
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

internal class RecipeRepositoryTest{
    private lateinit var repository: RecipeRepository
    private lateinit var serviceAPI: ServiceAPI
    private lateinit var mockWebServer: MockWebServer

    @Before
    public fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        serviceAPI = RetrofitHelper.testApiInstance(mockWebServer.url("/").toString())
        repository = RecipeRepository(serviceAPI)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `for no recipes, api must return empty with http code 200`() = runTest {
        val recipes = RecipeResponse(emptyList())
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(recipes))
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = repository.getRecipes()
        assertThat(actualResponse.body()?.results).hasSize(0)
    }

    @Test
    fun `for server error, api must return with http code 5xx`() = runTest {
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = repository.getRecipes()
        assertThat(actualResponse.code()).isEqualTo(HttpURLConnection.HTTP_INTERNAL_ERROR)
    }

    @Test
    fun `for server error, api must return with http code 5xx and error message`() = runTest {
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = repository.getRecipes()
        assertThat(actualResponse.code()).isEqualTo(HttpURLConnection.HTTP_INTERNAL_ERROR)

    }
}