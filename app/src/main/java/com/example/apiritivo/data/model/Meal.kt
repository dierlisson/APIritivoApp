package com.example.apiritivo.data.model

data class MealResponse(val meals: List<Meal>?)

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strInstructions: String?
)