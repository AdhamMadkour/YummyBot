package com.example.yummibot.business.recipe;

import lombok.Data;

@Data
public class ResultRecipe {
    private int id;
    private String title;
    private String image = "https://bitsofco.de/content/images/2018/12/broken-1.png";
    private String imageType;
}
