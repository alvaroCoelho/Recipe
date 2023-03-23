package com.recipe.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.recipe.data.model.recipe.RecipeModel
import com.recipe.ui.screens.Routers.DETAIL_SCREEN
import com.recipe.ui.screens.Routers.HOME_SCREEN
import com.recipe.ui.screens.Routers.MAP_SCREEN
import com.recipe.ui.screens.Routers.RECIPE

@Composable
fun NavigationHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = HOME_SCREEN) {

        composable(HOME_SCREEN){
            HomeScreen(navController = navController)
        }

        composable(DETAIL_SCREEN){
            val recipe = navController
                .previousBackStackEntry?.savedStateHandle?.get<RecipeModel>(RECIPE)
            recipe?.let {
                DetailScreen(it, navController)
            }
        }

        composable(MAP_SCREEN){
            val recipe = navController
                .previousBackStackEntry?.savedStateHandle?.get<RecipeModel>(RECIPE)
            recipe?.let {
                MapScreen(it)
            }
        }

    }

}

object Routers {
    const val HOME_SCREEN = "HOME_SCREEN"
    const val DETAIL_SCREEN = "DETAIL_SCREEN"
    const val MAP_SCREEN = "MAP_SCREEN"
    const val RECIPE = "RECIPE"
}