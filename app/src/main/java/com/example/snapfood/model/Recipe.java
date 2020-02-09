package com.example.snapfood.model;

import java.util.List;

public class Recipe {
    String name;
    List<String> hasIngredients;
    List<String> needIngredients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getHasIngredients() {
        return hasIngredients;
    }

    public void setHasIngredients(List<String> hasIngredients) {
        this.hasIngredients = hasIngredients;
    }

    public List<String> getNeedIngredients() {
        return needIngredients;
    }

    public void setNeedIngredients(List<String> needIngredients) {
        this.needIngredients = needIngredients;
    }

    public Recipe(String name) {
        this.name = name;
    }


}
