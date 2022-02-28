package com.example.yummibot.business.recipe;

import lombok.Data;

@Data
public class Ingredients {

    private String image;
    private String name;
    private Amount amount;

    Ingredients() {

    }


    public String getImage() {
        if (image != null) {
            image = "https://spoonacular.com/cdn/ingredients_250x250/" + image.replace("png", "jpg");
            return image;
        } else return "https://bitsofco.de/content/images/2018/12/broken-1.png";
    }
/*
    Ingredients(String name, String image) {
        this.name = name;
        this.image = "https://spoonacular.com/cdn/ingredients_250x250/" + image.replace("png", "jpg");
    }*/
}
