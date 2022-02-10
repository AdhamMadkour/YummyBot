package com.example.yummibot.business.recipe;

import com.example.yummibot.business.recipe.payload.Recipe;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class APISearchController {
    String url = "https://api.spoonacular.com/recipes/complexSearch";
    String apikey = "?apiKey=b7f84234d75d4f7ba1de453a856c3fe8";
    private final RecipeRepo recipeRepo;

    public APISearchController(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @GetMapping("/test")
    public List<ResultRecipe> Result(@RequestParam(name = "query") String query) {
        RestTemplate restTemplate = restTemplate(new RestTemplateBuilder());
        Results results = restTemplate.getForObject(url + apikey + "&query=" + query + "&number=10", Results.class);
        return results.front();
    }

    @GetMapping("/getbyid/{id}")
    public Recipe recipeExact(@PathVariable(name = "id") int id) {
        ResultRecipe resultRecipe = new ResultRecipe();
        resultRecipe.setId(id);
        return recipeRepo.getById(resultRecipe);
    }
}
