package com.example.snapfood.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    String name;
    int id;
    List<String> hasIngredients;
    List<String> needIngredients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", hasIngredients=" + hasIngredients +
                ", needIngredients=" + needIngredients +
                '}';
    }

    public Recipe(String name) {
        this.name = name;
    }

}
