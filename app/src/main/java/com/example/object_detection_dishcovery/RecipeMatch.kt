package com.example.object_detection_dishcovery

/**
 * Data class to represent a matching recipe with match percentage
 */
data class RecipeMatch(
    val recipe: RecipeData,
    val matchPercentage: Float,
    val matchedIngredients: List<String>
)
