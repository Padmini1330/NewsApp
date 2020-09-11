package com.example.newsaapppro.fields;


import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsaapppro.Adapter5;
import com.example.newsaapppro.R;
import com.example.newsaapppro.technology_news.ApiClient;
import com.example.newsaapppro.technology_news.t_headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class technology extends AppCompatActivity {
    private String Api_key = "087d2f39836f4c5ebac8a8486136224b";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Toolbar toolbar;
    Adapter5 adapter5;
    List<com.example.newsaapppro.technology_news.t_articles> t_articles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.technology);
        recyclerView = findViewById(R.id.t_recycler_view);
        progressBar = findViewById(R.id.t_progress);
        progressBar.setVisibility(View.VISIBLE);
        toolbar = findViewById(R.id.t_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Technology");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        String country = getCountry();
        retrieveJson(country);
    }

    public void retrieveJson(String country){
        Call<t_headlines> headlines = ApiClient.getInstance().getApi().getT_headlines(country,"technology",Api_key);
        headlines.enqueue(new Callback<t_headlines>() {
            @Override
            public void onResponse(Call<t_headlines> call, Response<t_headlines> response) {
                if(response.isSuccessful()&&response.body().getArticles()!=null){
//                        if(articles.isEmpty()){
//                            articles.clear();
//                        }
                    t_articles = response.body().getArticles();
                    adapter5 = new Adapter5(technology
                            .this,t_articles);
                    recyclerView.setAdapter(adapter5);
                    progressBar.setVisibility(View.GONE);
                    adapter5.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<t_headlines> call, Throwable t) {

            }
        });


    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();

    }
}
