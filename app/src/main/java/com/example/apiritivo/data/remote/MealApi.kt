package com.example.apiritivo.data.remote

import com.example.apiritivo.data.model.MealResponse
import retrofit2.Response
import retrofit2.http.GET

interface MealApi {
    @GET("api/json/v1/1/search.php?s=")
    suspend fun getMeals(): Response<MealResponse>
}