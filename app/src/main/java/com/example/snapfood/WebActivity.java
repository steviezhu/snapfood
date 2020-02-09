package com.example.snapfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        final WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);


        Intent intent = getIntent();
        if (intent!=null ){
            int id = intent.getIntExtra("id",-1);
            if(id!=-1){
                String secondUrl = "https://api.spoonacular.com/recipes/" + id + "/information?apiKey=875ac2239acb4b43b3aa038afd917dda";
                StringRequest newStringRequest = new StringRequest(Request.Method.GET, secondUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    String url = (String) jsonResponse.get("sourceUrl");
                                    Log.d("1234",url);
                                    webView.loadUrl(url);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("bugg",error.getMessage());
                        webView.loadUrl("https://www.google.com");                    }
                });
                RequestQueue secondQueue = Volley.newRequestQueue(this);
                secondQueue.add(newStringRequest);




            }
        }
    }
}
