package com.example.snapfood.caloriemama;

import android.graphics.Bitmap;

/**
 * Created by azumio.azumio
 */

public class FoodTask {

    private Bitmap image;
    private String token = "a3c387656349782ef7c4ea3c73165bc3";

    public FoodTask(Bitmap image) throws IllegalArgumentException {
        if ( image == null) {
            throw new IllegalArgumentException("Both parameters required");
        }
        this.image = image;
    }

    public String getToken() {
        return this.token;
    }

    public Bitmap getImage() {
        return this.image;
    }
}
