package com.recipe.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.recipe.R
import com.recipe.data.model.recipe.RecipeModel
import com.recipe.ui.screens.Routers.MAP_SCREEN
import com.recipe.ui.screens.Routers.RECIPE


@Composable
fun DetailScreen(recipeModel: RecipeModel,  navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model =recipeModel.image,
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }

            Row(modifier = Modifier.padding(5.dp)) {
                Text(
                    text =recipeModel.name,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(modifier = Modifier.padding(15.dp, 5.dp, 15.dp, 5.dp)) {
                Text(
                    text = recipeModel.description,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(modifier = Modifier.padding(15.dp, 5.dp, 15.dp, 5.dp)) {
                Text(
                    text = recipeModel.methodPreparation,
                    fontWeight = FontWeight.Bold
                )
            }

            Row (modifier = Modifier.padding(15.dp, 5.dp, 15.dp, 5.dp)) {
                Button(modifier = Modifier
                    .height(50.dp)
                    .width(150.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.teal_700),
                        contentColor = Color.White,
                    ),
                    onClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            RECIPE,
                            recipeModel
                        )
                        navController.navigate(MAP_SCREEN)
                    }) {
                    Text(
                        text = stringResource(R.string.see_on_map),
                        fontSize = 14.sp
                    )

                }

            }
        }
    }
}