package com.example.newsaapppro.fields;


import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsaapppro.Adapter1;
import com.example.newsaapppro.R;
import com.example.newsaapppro.business_news.ApiClient;
import com.example.newsaapppro.business_news.b_headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class business extends AppCompatActivity {
    private String Api_key = "087d2f39836f4c5ebac8a8486136224b";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Toolbar toolbar;
    Adapter1 adapter1;
    List<com.example.newsaapppro.business_news.b_articles> b_articles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business);
        recyclerView = findViewById(R.id.b_recycler_view);
        progressBar = findViewById(R.id.b_progress);
        progressBar.setVisibility(View.VISIBLE);
        toolbar = findViewById(R.id.b_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Business");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        String country = getCountry();
        retrieveJson(country);
    }

    public void retrieveJson(String country){
        Call<b_headlines> headlines = ApiClient.getInstance().getApi().getB_headlines(country,"business",Api_key);
        headlines.enqueue(new Callback<b_headlines>() {
            @Override
            public void onResponse(Call<b_headlines> call, Response<b_headlines> response) {
                if(response.isSuccessful()&&response.body().getArticles()!=null){
//                        if(articles.isEmpty()){
//                            articles.clear();
//                        }
                    b_articles = response.body().getArticles();
                    adapter1 = new Adapter1(business.this,b_articles);
                    recyclerView.setAdapter(adapter1);
                    progressBar.setVisibility(View.GONE);
                    adapter1.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<b_headlines> call, Throwable t) {

            }
        });


    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();

    }
}
