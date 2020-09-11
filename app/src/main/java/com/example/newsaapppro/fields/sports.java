package com.example.newsaapppro.fields;


import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsaapppro.Adapter3;
import com.example.newsaapppro.R;
import com.example.newsaapppro.sports_news.ApiClient;
import com.example.newsaapppro.sports_news.s_articles;
import com.example.newsaapppro.sports_news.s_headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sports extends AppCompatActivity {
    private String Api_key = "087d2f39836f4c5ebac8a8486136224b";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Toolbar toolbar;
    Adapter3 adapter3;
    List<s_articles> s_articles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports);
        recyclerView = findViewById(R.id.s_recycler_view);
        progressBar = findViewById(R.id.s_progress);
        progressBar.setVisibility(View.VISIBLE);
        toolbar = findViewById(R.id.s_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Sports");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        String country = getCountry();
        retrieveJson(country);
    }

    public void retrieveJson(String country){
        Call<s_headlines> headlines = ApiClient.getInstance().getApi().getS_headlines(country,"sports",Api_key);
        headlines.enqueue(new Callback<s_headlines>() {
            @Override
            public void onResponse(Call<s_headlines> call, Response<s_headlines> response) {
                if(response.isSuccessful()&&response.body().getArticles()!=null){
//                        if(articles.isEmpty()){
//                            articles.clear();
//                        }
                    s_articles = response.body().getArticles();
                    adapter3 = new Adapter3(sports.this,s_articles);
                    recyclerView.setAdapter(adapter3);
                    progressBar.setVisibility(View.GONE);
                    adapter3.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<s_headlines> call, Throwable t) {

            }
        });


    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();

    }
}
