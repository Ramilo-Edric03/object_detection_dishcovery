package com.example.object_detection_dishcovery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for displaying recipes in a RecyclerView
 */
class RecipeAdapter(
    private val recipes: List<RecipeMatch>,
    private val onRecipeClickListener: (RecipeData) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeName: TextView = itemView.findViewById(R.id.recipeName)
        val recipeMatch: TextView = itemView.findViewById(R.id.recipeMatch)
        val recipePrepTime: TextView = itemView.findViewById(R.id.recipePrepTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipeMatch = recipes[position]
        val recipe = recipeMatch.recipe

        holder.recipeName.text = recipe.name

        // Format match percentage as percentage
        val matchPercent = (recipeMatch.matchPercentage * 100).toInt()
        holder.recipeMatch.text = "Ingredients match: $matchPercent%"

        holder.recipePrepTime.text = "Prep time: ${recipe.prepTime} minutes"

        // Set click listener for the item
        holder.itemView.setOnClickListener {
            onRecipeClickListener(recipe)
        }
    }
}