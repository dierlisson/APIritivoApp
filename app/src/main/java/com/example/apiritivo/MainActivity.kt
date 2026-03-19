package com.example.apiritivo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apiritivo.ui.screens.RecipeDetailScreen
import com.example.apiritivo.ui.screens.RecipeListScreen
import com.example.apiritivo.ui.theme.APIritivoTheme
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiritivoApp()
        }
    }
}

@Composable
fun ApiritivoApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "recipe_list") {
        composable("recipe_list") {
            RecipeListScreen(
                onRecipeClick = { meal ->
                    // Codificamos a URL da imagem para não quebrar a rota de navegação
                    val encodedUrl = URLEncoder.encode(meal.strMealThumb, StandardCharsets.UTF_8.toString())
                    val instructions = meal.strInstructions ?: "Sem instruções."
                    val encodedInstructions = URLEncoder.encode(instructions, StandardCharsets.UTF_8.toString())

                    navController.navigate("recipe_detail/${meal.strMeal}/$encodedUrl/$encodedInstructions")
                }
            )
        }

        composable("recipe_detail/{name}/{url}/{instructions}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val url = URLDecoder.decode(backStackEntry.arguments?.getString("url") ?: "", StandardCharsets.UTF_8.toString())
            val instructions = URLDecoder.decode(backStackEntry.arguments?.getString("instructions") ?: "", StandardCharsets.UTF_8.toString())

            RecipeDetailScreen(
                mealName = name,
                mealImageUrl = url,
                mealInstructions = instructions,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    APIritivoTheme {
        Greeting("Android")
    }
}