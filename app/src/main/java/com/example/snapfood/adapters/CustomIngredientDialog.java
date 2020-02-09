package com.example.snapfood.adapters;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.snapfood.R;
import com.example.snapfood.model.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CustomIngredientDialog extends Dialog {
    Context context;
    List<String> ingredients;
    Button yesButton, noButton;
    ExampleDialogListener exampleDialogListener = new ExampleDialogListener() {
        @Override
        public void applyTexts(List<Recipe> recipes) {

        }
    };
    public CustomIngredientDialog(@NonNull Context context, List<String> strings) {
        super(context);
        this.context=context;
        this.ingredients=strings;
    }

    public CustomIngredientDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context=context;
    }

    protected CustomIngredientDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diaglog_confirm_ingredient);
        RecyclerView recyclerView = findViewById(R.id.confirmIngredientRecyclerview);
        final ConfirmIngredientAdapters confirmIngredientAdapters = new ConfirmIngredientAdapters(context,ingredients);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(confirmIngredientAdapters);
        confirmIngredientAdapters.notifyDataSetChanged();

        yesButton = findViewById(R.id.okbutton);
        noButton = findViewById(R.id.cancelButton);


        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get all the recipe list and make the api call
               HashSet<String > strings = confirmIngredientAdapters.getMyData();
                Log.d("bugg",strings.toString());
                makeAPICall(strings);
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void makeAPICall(HashSet<String> ingridients) {

        RequestQueue queue = Volley.newRequestQueue(context);
        final List<Recipe> recipes = new ArrayList<>();
        String url = "https://api.spoonacular.com/recipes/findByIngredients?apiKey=96c1ee6ca45b46d7a4a0ff1bf85533af&number=3&ingredients="+ingridients.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray recipesarray = new JSONArray(response);
                            for (int k = 0 ; k < recipesarray.length(); k++) {
                                JSONObject recipeOne = (JSONObject) recipesarray.get(k);
                                Recipe recipe = new Recipe(recipeOne.get("title")+"");
                                JSONArray usedOne = recipeOne.getJSONArray("usedIngredients");
                                List<String> usedIngridient = new ArrayList<>();
                                for (int i = 0; i < usedOne.length(); i++) {
                                    JSONObject usedIngredientArray = (JSONObject) usedOne.get(i);
                                    usedIngridient.add(usedIngredientArray.get("name")+"");
                                }

                                JSONArray missedOne = recipeOne.getJSONArray("missedIngredients");
                                List<String> missingIngrident = new ArrayList<>();

                                for (int i = 0; i < missedOne.length(); i++) {
                                    JSONObject missedIngredient = (JSONObject) missedOne.get(i);
                                    missingIngrident.add(missedIngredient.get("name")+"");
                                }
                                recipe.setHasIngredients(usedIngridient);
                                recipe.setNeedIngredients(missingIngrident);
                                recipes.add(recipe);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ((ExampleDialogListener)context).applyTexts(recipes);
                        dismiss();
                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(stringRequest);
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(listener);
    }

    public interface ExampleDialogListener {
        void applyTexts(List<Recipe> recipes);
    }
}
