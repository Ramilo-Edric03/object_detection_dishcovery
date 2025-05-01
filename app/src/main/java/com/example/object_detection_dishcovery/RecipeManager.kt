package com.example.object_detection_dishcovery

/**
 * Class to manage recipes and match ingredients to recipes
 */
class RecipeManager {
    // Sample recipes using only Apple, Banana, Carrot, Egg, and Tomato
    private val recipes = listOf(
        RecipeData(
            name = "Banana Apple Smoothie",
            ingredients = listOf("Apple", "Banana"),
            instructions = "1. Peel and chop the apple and banana\n2. Blend with ice and a splash of water\n3. Serve cold",
            prepTime = 5
        ),
        RecipeData(
            name = "Carrot Apple Salad",
            ingredients = listOf("Apple", "Carrot"),
            instructions = "1. Grate the apple and carrot\n2. Mix together in a bowl\n3. Add a drizzle of honey (optional)\n4. Serve fresh",
            prepTime = 10
        ),
        RecipeData(
            name = "Scrambled Eggs with Tomato",
            ingredients = listOf("Egg", "Tomato"),
            instructions = "1. Beat the eggs in a bowl\n2. Dice the tomato\n3. Heat a pan and cook the eggs\n4. Add tomatoes when eggs are halfway done\n5. Season with salt and pepper",
            prepTime = 8
        ),
        RecipeData(
            name = "Carrot Tomato Soup",
            ingredients = listOf("Carrot", "Tomato"),
            instructions = "1. Chop carrots and tomatoes\n2. Boil in water until soft\n3. Blend until smooth\n4. Season with salt and herbs",
            prepTime = 20
        ),
        RecipeData(
            name = "Banana Egg Pancakes",
            ingredients = listOf("Banana", "Egg"),
            instructions = "1. Mash the banana\n2. Beat the eggs\n3. Mix banana and eggs together\n4. Pour small amounts into a hot pan\n5. Flip when bubbles appear\n6. Serve warm",
            prepTime = 15
        ),
        RecipeData(
            name = "Veggie Breakfast Scramble",
            ingredients = listOf("Egg", "Tomato", "Carrot"),
            instructions = "1. Dice tomato and grate carrot\n2. Beat eggs in a bowl\n3. Cook vegetables in a pan until soft\n4. Add eggs and scramble together\n5. Season to taste",
            prepTime = 12
        ),
        RecipeData(
            name = "Fruity Carrot Smoothie",
            ingredients = listOf("Apple", "Banana", "Carrot"),
            instructions = "1. Peel and chop apple and banana\n2. Grate or chop carrot\n3. Blend all ingredients with ice\n4. Add a little water if needed\n5. Serve immediately",
            prepTime = 7
        )
    )

    /**
     * Find recipes that match the detected ingredients
     * @param detectedIngredients List of detected ingredient names
     * @return List of matching recipes sorted by match percentage
     */
    fun findRecipesWithIngredients(detectedIngredients: List<String>): List<RecipeMatch> {
        val recipeMatches = mutableListOf<RecipeMatch>()

        for (recipe in recipes) {
            val matchedIngredients = recipe.ingredients.filter {
                    ingredient -> detectedIngredients.contains(ingredient)
            }

            if (matchedIngredients.isNotEmpty()) {
                val matchPercentage = matchedIngredients.size.toFloat() / recipe.ingredients.size

                recipeMatches.add(
                    RecipeMatch(
                        recipe = recipe,
                        matchPercentage = matchPercentage,
                        matchedIngredients = matchedIngredients
                    )
                )
            }
        }

        // Sort by match percentage in descending order
        return recipeMatches.sortedByDescending { it.matchPercentage }
    }

    companion object
}