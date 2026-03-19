package com.example.apiritivo.ui.viewmodel

import com.example.apiritivo.data.model.Meal
import com.example.apiritivo.data.model.MealResponse
import com.example.apiritivo.data.remote.MealApi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class RecipeViewModelTest {

    private lateinit var viewModel: RecipeViewModel
    private val api: MealApi = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchRecipes emite Success quando a API retorna sucesso`() = runTest {
        val mockMeals = listOf(Meal("1", "Pizza", "url", "Instruções"))
        coEvery { api.getMeals() } returns Response.success(MealResponse(mockMeals))

        viewModel = RecipeViewModel(api)
        advanceUntilIdle() // Aguarda as coroutines finalizarem

        val state = viewModel.uiState.value
        assertTrue(state is RecipeUiState.Success)
        assertEquals(mockMeals, (state as RecipeUiState.Success).recipes)
    }

    @Test
    fun `fetchRecipes emite Error quando ocorre uma Exception (Falha de rede)`() = runTest {
        coEvery { api.getMeals() } throws RuntimeException("Sem internet")

        viewModel = RecipeViewModel(api)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state is RecipeUiState.Error)
        assertEquals("Falha de rede. Verifique sua conexão.", (state as RecipeUiState.Error).message)
    }
}