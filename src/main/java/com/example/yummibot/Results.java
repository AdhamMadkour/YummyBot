package com.example.yummibot;

import lombok.Data;

import java.util.List;

@Data
public class Results {
    private List<ResultRecipe> results;
    private int offset;
    private int number;
    private int totalResults;

    public List<ResultRecipe> front() {
        return results;
    }
}
