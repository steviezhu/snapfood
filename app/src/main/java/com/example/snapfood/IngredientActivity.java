package com.example.snapfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.snapfood.adapters.ConfirmIngredientAdapters;
import com.example.snapfood.adapters.CustomIngredientDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class IngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        List<String> ingridients =getListOfIngridientFromIntent();

        setupTextView(ingridients);
    }

    private void setupTextView(List<String> ingridients) {

        TextView textView = findViewById(R.id.textView3);
        StringBuilder sa= new StringBuilder();
        HashSet<String> hashSet = new HashSet<>(ingridients);
        for (String s:hashSet){
            sa.append(" ").append(s);
        }
        Log.d("qwerty",sa.toString());
        textView.setText(sa.toString());
        setupRecyclerview(hashSet);
    }

    private void setupRecyclerview(HashSet<String> hashSet) {
        List<String> strings = new ArrayList<String>(hashSet);
        Log.d("qwerty","size: "+ strings.size());
        CustomIngredientDialog customIngredientDialog = new CustomIngredientDialog(this, strings);
        customIngredientDialog.setCancelable(false);
        customIngredientDialog.show();


    }

    private List<String> getListOfIngridientFromIntent() {
        Intent intent = getIntent();
       String json = intent.getStringExtra("json");
        JSONObject response = null;

        List<String> list = new ArrayList<>();

        try {
            response = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (response != null) {
            JSONArray results = response.optJSONArray("results");
            for (int i=0; i<results.length(); i++) {
                JSONObject result = results.optJSONObject(i);
                JSONArray items = result.optJSONArray("items");
                Log.d("buggg",items.toString());
                for (int j=0; j<items.length(); j++) {
                    JSONObject item = items.optJSONObject(j);
                    Log.d("bugggg",item.toString());

                    list.add(item.optString("group"));
                }
            }
        }


        return list;
    }

}
