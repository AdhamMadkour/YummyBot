package com.example.yummibot.business.recipe;

import com.example.yummibot.business.recipe.payload.Recipe;

public interface RecipeRepo {
    Recipe getById(ResultRecipe resultRecipe);
}
