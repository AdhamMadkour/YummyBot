package com.example.yummibot.business.recipe;

import com.example.yummibot.business.recipe.payload.Recipe;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class APISearchController {
    String urlForComplexSearch = "https://api.spoonacular.com/recipes/complexSearch";
    String apikey = "?apiKey=b7f84234d75d4f7ba1de453a856c3fe8";
    String adhamKey = "?apiKey=21b6a1afab9646f7a125f7ba0331f024";
    String urlForRandomRecipe = "https://api.spoonacular.com/recipes/random";
    String youtubeSearchURL = "https://youtube.googleapis.com/youtube/v3/search?key=AIzaSyArfdkorIe3mx059e3qcqLKbETnEx796AA&q=";
    private final RecipeRepo recipeRepo;

    public APISearchController(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @GetMapping("/{name}/{offset}")
    public List<ResultRecipe> Result(@PathVariable(name = "name") String name, @PathVariable(name = "offset") String offset) {
        RestTemplate restTemplate = restTemplate(new RestTemplateBuilder());
        return restTemplate.exchange(urlForComplexSearch + apikey + "&query=" + name + "&number=10" + "&offset=" + offset, HttpMethod.GET, setHeaders(), Results.class).getBody().getResults();
    }

    public HttpEntity<String> setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-agent", "app");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }

    @GetMapping("/getbyid/{id}")
    public Recipe recipeExact(@PathVariable(name = "id") int id) {
        ResultRecipe resultRecipe = new ResultRecipe();
        resultRecipe.setId(id);
        return recipeRepo.getById(resultRecipe);
    }

    @GetMapping("/belowTwenty/{offset}")
    public List<ResultRecipe> randomRecipe(@PathVariable(name = "offset") String offset) {
        RestTemplate restTemplate = restTemplate(new RestTemplateBuilder());
        return restTemplate.exchange(urlForComplexSearch + adhamKey + "&maxReadyTime=20" + "&number=10" + "&offset=" + offset, HttpMethod.GET, setHeaders(), Results.class).getBody().getResults();
    }

    @GetMapping("/random")
    public List<ResultRecipe> surpriseMe() {
        RestTemplate restTemplate = restTemplate(new RestTemplateBuilder());
        return restTemplate.exchange(urlForRandomRecipe + apikey, HttpMethod.GET, setHeaders(), Results.class).getBody().getRecipes();
    }

    @GetMapping("/mealType/{mealtype}")
    public List<ResultRecipe> mealType(@PathVariable(name = "mealtype") String mealtype) {
        RestTemplate restTemplate = restTemplate(new RestTemplateBuilder());
        return restTemplate.exchange(urlForRandomRecipe + apikey + "&tags=" + mealtype + "&number=10", HttpMethod.GET, setHeaders(), Results.class).getBody().getRecipes();
    }

    @GetMapping("/youtuberesults/{recipe}")
    public List<YoutubeSearch> youtubeSearches(@PathVariable(name = "recipe") String recipe) {
        RestTemplate restTemplate = restTemplate(new RestTemplateBuilder());
        return restTemplate.exchange(youtubeSearchURL + recipe + " recipe", HttpMethod.GET, setHeaders(), YoutubeResults.class).getBody().getVideoById();
    }


}
