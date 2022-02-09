package com.example.yummibot;
import com.example.yummibot.ResultRecipe;
import com.example.yummibot.Results;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.yummibot.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class APISearchController {
    String url = "https://api.spoonacular.com/recipes/complexSearch";
    String apikey = "?apiKey=b7f84234d75d4f7ba1de453a856c3fe8";

    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @GetMapping("/hello")
        public List<ResultRecipe> Result(@RequestParam(name = "query") String query) {
        RestTemplate restTemplate = restTemplate(new RestTemplateBuilder());
        Results results = restTemplate.getForObject(url + apikey + "&query=" + query + "&number=10", Results.class);
        return results.front();

    }
    @GetMapping("/hello1")
    public String sayHello(){
        return "HELLO";
    }
}
