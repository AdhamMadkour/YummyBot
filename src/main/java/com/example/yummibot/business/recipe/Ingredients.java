package com.example.yummibot.business.recipe;

import lombok.Data;

@Data
public class Ingredients {
    private String image;
    private String name;

    Ingredients(String name, String image) {
        this.name = name;
        this.image = "https://spoonacular.com/cdn/ingredients_250x250/"+image.replace("png","jpg");
    }
}
