package com.recipe.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recipe.data.model.recipe.RecipeModel
import com.recipe.data.model.recipe.RecipeResponse
import com.recipe.ui.useCase.GetRecipesUseCase
import com.recipe.util.Constants.CONNECT_ERROR
import com.recipe.util.Constants.DATA_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ListRecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {
   private val _list: MutableState<ResourceState>  = mutableStateOf(ResourceState.Loading)
    val list: State<ResourceState> = _list

init {
fetch()
}

    private fun fetch() = viewModelScope.launch {
      getRecipes()
    }

    private suspend fun getRecipes() {

        try {
            val response = getRecipesUseCase.invoke()
            _list.value = handleResponse(response)

        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> _list.value = ResourceState.Error(CONNECT_ERROR)
                else -> _list.value = ResourceState.Error(DATA_ERROR)
            }
        }

    }


    private fun handleResponse(response: Response<RecipeResponse>): ResourceState {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                return ResourceState.Success(values.results)
            }
        }
        return ResourceState.Error(response.message())
    }
}

sealed interface ResourceState {
    object Loading : ResourceState
    class Success(
        val allRecipes: List<RecipeModel>
    ) : ResourceState

    class Error(
        val Message: String
    ) : ResourceState

    object Empty : ResourceState
}