package com.example.apiritivo.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.apiritivo.data.model.Meal
import com.example.apiritivo.ui.viewmodel.RecipeUiState
import com.example.apiritivo.ui.viewmodel.RecipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(
    viewModel: RecipeViewModel = hiltViewModel(),
    onRecipeClick: (Meal) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("APIritivo") }) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) {
            when (state) {
                is RecipeUiState.Loading -> CircularProgressIndicator()
                is RecipeUiState.Error -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = (state as RecipeUiState.Error).message)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.fetchRecipes() }) { Text("Tentar Novamente") }
                    }
                }
                is RecipeUiState.Success -> {
                    val recipes = (state as RecipeUiState.Success).recipes
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(recipes) { meal ->
                            RecipeItem(meal = meal, onClick = { onRecipeClick(meal) })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeItem(meal: Meal, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onClick() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = meal.strMealThumb,
                contentDescription = "Imagem de ${meal.strMeal}",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = meal.strMeal, style = MaterialTheme.typography.titleMedium)
        }
    }
}