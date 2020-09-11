package com.example.newsaapppro.fields;


import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsaapppro.Adapter6;
import com.example.newsaapppro.R;
import com.example.newsaapppro.entertainment_news.ApiClient;
import com.example.newsaapppro.entertainment_news.e_headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class entertainment extends AppCompatActivity {
    private String Api_key = "087d2f39836f4c5ebac8a8486136224b";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Toolbar toolbar;
    Adapter6 adapter6;
    List<com.example.newsaapppro.entertainment_news.e_articles> e_articles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entertainment);
        recyclerView = findViewById(R.id.e_recycler_view);
        progressBar = findViewById(R.id.e_progress);
        progressBar.setVisibility(View.VISIBLE);
        toolbar = findViewById(R.id.e_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Entertainment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        String country = getCountry();
        retrieveJson(country);
    }

    public void retrieveJson(String country){
        Call<e_headlines> headlines = ApiClient.getInstance().getApi().getE_headlines(country,"entertainment",Api_key);
        headlines.enqueue(new Callback<e_headlines>() {
            @Override
            public void onResponse(Call<e_headlines> call, Response<e_headlines> response) {
                if(response.isSuccessful()&&response.body().getArticles()!=null){
//                        if(articles.isEmpty()){
//                            articles.clear();
//                        }
                    e_articles = response.body().getArticles();
                    adapter6 = new Adapter6(entertainment.this,e_articles);
                    recyclerView.setAdapter(adapter6);
                    progressBar.setVisibility(View.GONE);
                    adapter6.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<e_headlines> call, Throwable t) {

            }
        });


    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();

    }
}
