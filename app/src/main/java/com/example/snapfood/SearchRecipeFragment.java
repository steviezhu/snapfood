package com.example.snapfood;

import android.content.SharedPreferences;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.snapfood.adapters.RecipeAdapter;
import com.example.snapfood.model.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

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




        String MyPREFERENCES = "MyPrefs" ;
        SharedPreferences prefs = getActivity().getSharedPreferences(MyPREFERENCES , MODE_PRIVATE);
        String restoredText = prefs.getString("recipes", null);
        Log.d("qweerty","list is here!"+ restoredText);
        if(restoredText!=null && !restoredText.equals("")){
            setuprecyclerforactuallist(restoredText,view);
        }
        return view;

    }

    private void setuprecyclerforactuallist(String restoredText,View view) {
        Gson gson = new Gson();
        Type listOfTestObject = new TypeToken<List<Recipe>>(){}.getType();
        List<Recipe> list2 = gson.fromJson(restoredText, listOfTestObject);
        RecipeAdapter recipeAdapter = new RecipeAdapter(list2,getActivity());
        Log.d("qwerty","sdsdsd: "+ list2.toString());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.recipeRecyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recipeAdapter);
        recipeAdapter.notifyDataSetChanged();
    }
}
