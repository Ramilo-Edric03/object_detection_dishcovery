package com.example.object_detection_dishcovery
/**
 * Data class for storing recipe information
 */
data class RecipeData(
    val name: String,
    val ingredients: List<String>,
    val instructions: String,
    val prepTime: Int  // in minutes
)