package com.example.yummibot.business.recipe;

import com.example.yummibot.business.recipe.payload.Recipe;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class RecipeRepoCache implements RecipeRepo {
    @Override
    @Cacheable("Recipe")
    public Recipe getById(ResultRecipe resultRecipe) {
        return new Recipe(resultRecipe);
    }
}
