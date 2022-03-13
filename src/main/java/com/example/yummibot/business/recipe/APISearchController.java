package com.example.yummibot.business.recipe;
import com.example.yummibot.business.recipe.payload.Recipe;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@AllArgsConstructor
public class APISearchController {

    private final RecipeRepo recipeRepo;
    private final SpoonacularClient spoonacularClient;


    @GetMapping("/{name}/{offset}")
    public List<ResultRecipe> Result(@PathVariable(name = "name") String name, @PathVariable(name = "offset") String offset) {
        return spoonacularClient.getRecipes(name, offset);
    }


    @GetMapping("/getbyid/{id}")
    public Recipe recipeExact(@PathVariable(name = "id") int id) {
        ResultRecipe resultRecipe = new ResultRecipe();
        resultRecipe.setId(id);
        return recipeRepo.getById(resultRecipe);
    }

    @GetMapping("/belowTwenty/{offset}")
    public List<ResultRecipe> randomRecipe(@PathVariable(name = "offset") String offset) {
        return spoonacularClient.getRecipesBelowTwenty(offset);
    }

    @GetMapping("/random")
    public List<ResultRecipe> surpriseMe() {
        return spoonacularClient.surpriseTheClient();
    }

    @GetMapping("/mealType/{mealtype}")
    public List<ResultRecipe> mealType(@PathVariable(name = "mealtype") String mealtype) {
        return spoonacularClient.getMealType(mealtype);
    }

    @GetMapping("/youtuberesults/{recipe}")
    public List<YoutubeSearch> youtubeSearches(@PathVariable(name = "recipe") String recipe) {
        return spoonacularClient.getRecipeVideos(recipe);
    }

    @GetMapping("/ingredients/{id}")
    public List<Ingredients> showIngredients(@PathVariable(name = "id") String id) {
        return spoonacularClient.ingredientsResults(id);
    }
    @GetMapping("/Fruits")
    public List<ResultRecipe> showFruits() {
        return spoonacularClient.getFruits();
    }


}
