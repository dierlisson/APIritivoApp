package com.example.apiritivo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiritivo.data.model.Meal
import com.example.apiritivo.data.remote.MealApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RecipeUiState {
    object Loading : RecipeUiState()
    data class Success(val recipes: List<Meal>) : RecipeUiState()
    data class Error(val message: String) : RecipeUiState()
}

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val api: MealApi
) : ViewModel() {

    private val _uiState = MutableStateFlow<RecipeUiState>(RecipeUiState.Loading)
    val uiState: StateFlow<RecipeUiState> = _uiState.asStateFlow()

    init {
        fetchRecipes()
    }

    fun fetchRecipes() {
        _uiState.value = RecipeUiState.Loading
        viewModelScope.launch {
            try {
                val response = api.getMeals()
                if (response.isSuccessful && response.body()?.meals != null) {
                    _uiState.value = RecipeUiState.Success(response.body()!!.meals!!)
                } else {
                    _uiState.value = RecipeUiState.Error("Não foi possível carregar as receitas.")
                }
            } catch (e: Exception) {
                _uiState.value = RecipeUiState.Error("Falha de rede. Verifique sua conexão.")
            }
        }
    }
}