package com.recipe.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.recipe.R
import com.recipe.data.model.recipe.RecipeModel
import com.recipe.ui.components.SearchRecipesView
import com.recipe.ui.screens.Routers.DETAIL_SCREEN
import com.recipe.ui.screens.Routers.RECIPE
import com.recipe.ui.viewModel.ListRecipesViewModel
import com.recipe.ui.viewModel.ResourceState
import com.recipe.util.Constants.CONNECT_ERROR
import com.recipe.util.Constants.DATA_ERROR

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: ListRecipesViewModel = hiltViewModel()
) {

    val currentState = viewModel.list.value

    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(color = Color.White)
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Text(
                text = stringResource(R.string.title_list_recipes)
            )
        }
        Row {
            SearchRecipesView(state = textState)
        }

        Row(modifier = Modifier.padding(5.dp)) {
            when (currentState) {
                ResourceState.Empty -> Empty()
                is ResourceState.Success -> ListRecipes(
                    currentState.allRecipes,
                    navController,
                    textState
                )
                is ResourceState.Loading -> Loading()
                is ResourceState.Error -> Error(currentState.Message)
            }
        }
    }
}


@Composable
fun ListRecipes(
    listRecipes: List<RecipeModel>,
    navController: NavController,
    state: MutableState<TextFieldValue>

) {

    var filteredItems: List<RecipeModel>
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {

        val searchedText = state.value.text

        filteredItems = if (searchedText.isEmpty()) {
            listRecipes
        } else {
            val resultList = ArrayList<RecipeModel>()

            for (recipe in listRecipes) {

                if (recipe.name.lowercase().contains(searchedText.lowercase())) {
                    resultList.add(recipe)

                } else {
                    for (ingredients in recipe.ingredients) {
                        if (ingredients.name.lowercase().contains(searchedText.lowercase())) {
                            resultList.add(recipe)
                        }
                    }
                }
            }
            resultList
        }
        items(filteredItems) { recipe ->
            Card(
                modifier = Modifier
                    .padding(2.dp)
                    .clickable {
                        navController.currentBackStackEntry?.savedStateHandle?.set(RECIPE, recipe)
                        navController.navigate(DETAIL_SCREEN)
                    },
                elevation = 10.dp,
            ) {
                ItemList(recipe)
            }
        }
    }
}

@Composable
fun ItemList(recipeModel: RecipeModel) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)) {
                AsyncImage(
                    modifier = Modifier
                        .size(100.dp),
                    model = recipeModel.image,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }

            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = recipeModel.name, fontWeight = FontWeight.Bold
                )
                Text(
                    text = recipeModel.description, fontWeight = FontWeight.Bold
                )
            }
        }
    }

}


@Composable
fun Loading() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp, 5.dp)
            .background(Color.White)
    ) {
        Text(
            text = stringResource(R.string.Loading),
            color = Color.Gray,
            fontSize = 20.sp
        )
    }
}

@Composable
fun Empty() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp, 5.dp)
            .background(Color.White)
            .padding(0.dp, 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.empty),
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 20.sp
        )
    }
}

@Composable
fun Error(erro: String) {
    val context = LocalContext.current

    val messageError = when (erro) {
        CONNECT_ERROR -> stringResource(id = R.string.error_connection)
        DATA_ERROR -> stringResource(id = R.string.data_error)
        else -> {
            stringResource(id = R.string.error)
        }
    }
    Toast.makeText(
        context,
        messageError,
        Toast.LENGTH_SHORT
    ).show()
}
