package com.example.yummibot.business.recipe;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SpoonacularClient {

    @Value("${spoonacular.api.base-url}")
    private String baseUrl;

    @Value("${spoonacular.api.complex-search-url}")
    private String spoonacularComplexSearchUrl;

    @Value("${spoonacular.api.random-recipe-url}")
    private String spoonacularRandomRecipeUrl;

    @Value("${spoonacular.api.key}")
    private String spoonacularApiKey;

    @Value("${spoonacular.api.search-recipe}")
    private String spoonacularSearch;

    @Value("${youtube.api.base-url}")
    private String youtubeBaseUrl;

    @Value("${youtube.api.search-url}")
    private String youtubeSearchUrl;

    @Value("${youtube.api.key}")
    private String youtubeApiKey;


    private final RestTemplate restTemplate;

    private HttpEntity<String> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-agent", "app");
        return new HttpEntity<>(headers);
    }

    private String getSpoonacularComplexSearchUrl(String name, String offset) {
        // https://api.spoonacular.com/recipes/complexSearch21b6a1afab9646f7a125f7ba0331f024&query=Meats&number=1
        return baseUrl + spoonacularComplexSearchUrl +"?apiKey="+ spoonacularApiKey + "&query=" + name + "&number=10&offset=" + offset;
    }

    private String getSpoonacularComplexSearchUrlForBelowTwenty(String offset) {
        return baseUrl + spoonacularComplexSearchUrl +"?apiKey="+ spoonacularApiKey + "&maxReadyTime=20&number=10&offset=" + offset;
    }

    private String getSpoonacularRandomRecipeUrl() {
        return baseUrl + spoonacularRandomRecipeUrl + "?apiKey="+ spoonacularApiKey;
    }

    private String getSpoonacularMealTypeRecipe(String mealType) {
        return baseUrl + spoonacularRandomRecipeUrl +"?apiKey="+ spoonacularApiKey + "&number=10&tags=" + mealType;
    }
    private String getRecipeIngredients(String id){
        //https://api.spoonacular.com/recipes/659927/ingredientWidget.json?apiKey=b7f84234d75d4f7ba1de453a856c3fe8
        return baseUrl+spoonacularSearch+"/"+id+"/ingredientWidget.json?apiKey="+spoonacularApiKey;
    }
    private String getYoutubeSearchResult(String recipe) {
        return youtubeBaseUrl + youtubeSearchUrl + "?key=" + youtubeApiKey + "&q=" + recipe;
    }


    public List<ResultRecipe> getRecipes(String name, String offset) {
        ResponseEntity<Results> exchange = restTemplate.exchange(
                getSpoonacularComplexSearchUrl(name, offset),
                HttpMethod.GET,
                getHeaders(),
                Results.class
        );
        if (Objects.isNull(exchange.getBody())) {
            return new ArrayList<>();
        } else {
            return exchange.getBody().getResults();
        }
    }

    public List<ResultRecipe> getRecipesBelowTwenty(String offset) {
        ResponseEntity<Results> exchange = restTemplate.exchange(
                getSpoonacularComplexSearchUrlForBelowTwenty(offset),
                HttpMethod.GET,
                getHeaders(),
                Results.class
        );
        if (Objects.isNull(exchange.getBody())) {
            return new ArrayList<>();
        } else {
            return exchange.getBody().getResults();
        }
    }

    public List<ResultRecipe> surpriseTheClient() {
        ResponseEntity<Results> exchange = restTemplate.exchange(
                getSpoonacularRandomRecipeUrl(),
                HttpMethod.GET,
                getHeaders(),
                Results.class
        );
        if (Objects.isNull(exchange.getBody())) {
            return new ArrayList<>();
        } else {
            return exchange.getBody().getRecipes();
        }
    }

    public List<ResultRecipe> getMealType(String mealType) {
        ResponseEntity<Results> exchange = restTemplate.exchange(
                getSpoonacularMealTypeRecipe(mealType),
                HttpMethod.GET,
                getHeaders(),
                Results.class
        );
        if (Objects.isNull(exchange.getBody())) {
            return new ArrayList<>();
        } else {
            return exchange.getBody().getRecipes();
        }
    }

    public List<YoutubeSearch> getRecipeVideos(String recipe) {
        ResponseEntity<YoutubeResults> exchange = restTemplate.exchange(
                getYoutubeSearchResult(recipe),
                HttpMethod.GET,
                getHeaders(),
                YoutubeResults.class
        );
        if (Objects.isNull(exchange.getBody())) {
            return new ArrayList<>();
        } else {
            return exchange.getBody().getVideoById();
        }
    }
    public List<Ingredients> ingredientsResults(String id) {

        ResponseEntity<Results> exchange = restTemplate.exchange(
              getRecipeIngredients(id),
                HttpMethod.GET,
                getHeaders(),
                Results.class
        );
        if (Objects.isNull(exchange.getBody())) {
            return new ArrayList<>();
        } else {
            return exchange.getBody().getIngredients();
        }
    }

}
