package com.example.newsaapppro.fields;


import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsaapppro.Adapter2;
import com.example.newsaapppro.R;
import com.example.newsaapppro.health_news.ApiClient;
import com.example.newsaapppro.health_news.h_headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class health extends AppCompatActivity {
    private String Api_key = "087d2f39836f4c5ebac8a8486136224b";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Toolbar toolbar;
    Adapter2 adapter2;
    List<com.example.newsaapppro.health_news.h_articles> h_articles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health);
        recyclerView = findViewById(R.id.h_recycler_view);
        progressBar = findViewById(R.id.h_progress);
        progressBar.setVisibility(View.VISIBLE);
        toolbar = findViewById(R.id.h_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Health");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        String country = getCountry();
        retrieveJson(country);
    }

    public void retrieveJson(String country){
        Call<h_headlines> headlines = ApiClient.getInstance().getApi().getH_headlines(country,"health",Api_key);
        headlines.enqueue(new Callback<h_headlines>() {
            @Override
            public void onResponse(Call<h_headlines> call, Response<h_headlines> response) {
                if(response.isSuccessful()&&response.body().getArticles()!=null){
//                        if(articles.isEmpty()){
//                            articles.clear();
//                        }
                    h_articles = response.body().getArticles();
                    adapter2 = new Adapter2(health.this,h_articles);
                    recyclerView.setAdapter(adapter2);
                    progressBar.setVisibility(View.GONE);
                    adapter2.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<h_headlines> call, Throwable t) {

            }
        });


    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();

    }
}
