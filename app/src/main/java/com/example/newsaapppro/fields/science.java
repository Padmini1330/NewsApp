package com.example.newsaapppro.fields;


import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsaapppro.Adapter4;
import com.example.newsaapppro.R;
import com.example.newsaapppro.science_news.ApiClient;
import com.example.newsaapppro.science_news.sci_headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class science extends AppCompatActivity {
    private String Api_key = "087d2f39836f4c5ebac8a8486136224b";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Toolbar toolbar;
    Adapter4 adapter4;
    List<com.example.newsaapppro.science_news.sci_articles> sci_articles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.science);
        recyclerView = findViewById(R.id.sci_recycler_view);
        progressBar = findViewById(R.id.sci_progress);
        progressBar.setVisibility(View.VISIBLE);
        toolbar = findViewById(R.id.sci_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Science");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        String country = getCountry();
        retrieveJson(country);
    }

    public void retrieveJson(String country){
        Call<sci_headlines> headlines = ApiClient.getInstance().getApi().getSci_headlines(country,"science",Api_key);
        headlines.enqueue(new Callback<sci_headlines>() {
            @Override
            public void onResponse(Call<sci_headlines> call, Response<sci_headlines> response) {
                if(response.isSuccessful()&&response.body().getArticles()!=null){
//                        if(articles.isEmpty()){
//                            articles.clear();
//                        }
                    sci_articles = response.body().getArticles();
                    adapter4 = new Adapter4(science.this,sci_articles);
                    recyclerView.setAdapter(adapter4);
                    progressBar.setVisibility(View.GONE);
                    adapter4.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<sci_headlines> call, Throwable t) {

            }
        });


    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();

    }
}
