package com.example.snapfood;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchRecipeFragment extends Fragment {

    EditText ingredients;
    TextView results;
    Button search;

    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static SearchRecipeFragment newInstance(int page, String title) {
        SearchRecipeFragment fragmentFirst = new SearchRecipeFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");


    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_recipe, container, false);

        ingredients = view.findViewById(R.id.ingredients);
        results = view.findViewById(R.id.recipe);
        search = view.findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                String url = "https://api.spoonacular.com/recipes/findByIngredients?apiKey=96c1ee6ca45b46d7a4a0ff1bf85533af&number=3&ingredients="+ingredients.getText();
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                results.setText(" ");
                                try {
                                    JSONArray recipes = new JSONArray(response);
                                    for (int k = 0 ; k < recipes.length(); k++) {
                                        JSONObject recipeOne = (JSONObject) recipes.get(k);
                                        results.append("\nName of Dish: " + recipeOne.get("title"));

                                        JSONArray usedOne = recipeOne.getJSONArray("usedIngredients");
                                        results.append("\nUsed Ingredients: \n");
                                        for (int i = 0; i < usedOne.length(); i++) {
                                            JSONObject usedIngredient = (JSONObject) usedOne.get(i);
                                            results.append(i + 1 + ". " + usedIngredient.get("name") + "\n");
                                        }

                                        JSONArray missedOne = recipeOne.getJSONArray("missedIngredients");
                                        results.append("\nMissed Ingredients: \n");
                                        for (int i = 0; i < missedOne.length(); i++) {
                                            JSONObject missedIngredient = (JSONObject) missedOne.get(i);
                                            results.append(i + 1 + ". " + missedIngredient.get("name") + "\n");
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        results.setText("Failed");
                    }
                });

                queue.add(stringRequest);
            }
        });

        return view;
    }
}
