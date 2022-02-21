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
        return spoonacularClient.getRecipes(name,offset);
//        return Objects.requireNonNull(restTemplate.exchange(urlForComplexSearch + apikey + "&query=" + name + "&number=10" + "&offset=" + offset, HttpMethod.GET, setHeaders(), Results.class).getBody()).getResults();
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
       // return restTemplate.exchange(urlForComplexSearch + adhamKey + "&maxReadyTime=20" + "&number=10" + "&offset=" + offset, HttpMethod.GET, setHeaders(), Results.class).getBody().getResults();
    }

    @GetMapping("/random")
    public List<ResultRecipe> surpriseMe() {
        return spoonacularClient.surpriseTheClient();
      //  return restTemplate.exchange(urlForRandomRecipe + apikey, HttpMethod.GET, setHeaders(), Results.class).getBody().getRecipes();
    }

    @GetMapping("/mealType/{mealtype}")
    public List<ResultRecipe> mealType(@PathVariable(name = "mealtype") String mealtype) {
       // return restTemplate.exchange(urlForRandomRecipe + apikey + "&tags=" + mealtype + "&number=10", HttpMethod.GET, setHeaders(), Results.class).getBody().getRecipes();
        return spoonacularClient.getMealType(mealtype);
    }

    @GetMapping("/youtuberesults/{recipe}")
    public List<YoutubeSearch> youtubeSearches(@PathVariable(name = "recipe") String recipe) {
       // return restTemplate.exchange(youtubeSearchURL + recipe + " recipe&maxResults=3", HttpMethod.GET, setHeaders(), YoutubeResults.class).getBody().getVideoById();
        return spoonacularClient.getRecipeVideos(recipe);
    }


}
