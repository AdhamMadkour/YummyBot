package com.example.yummibot.business.recipe;

import lombok.Data;

import java.util.Objects;

@Data
public class ResultRecipe {
    private int id;
    private String title;
    private String image ;
    private String imageType;

    ResultRecipe() {

    }

}
