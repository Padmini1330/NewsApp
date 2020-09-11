package com.example.newsaapppro;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsaapppro.Api.ApiClient;
import com.example.newsaapppro.Api.ApiInterface;
import com.example.newsaapppro.fields.business;
import com.example.newsaapppro.fields.entertainment;
import com.example.newsaapppro.fields.health;
import com.example.newsaapppro.fields.science;
import com.example.newsaapppro.fields.sports;
import com.example.newsaapppro.fields.technology;
import com.example.newsaapppro.headlines.Articles;
import com.example.newsaapppro.headlines.Headlines;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class news_headlines extends AppCompatActivity {
    private String api_key = "087d2f39836f4c5ebac8a8486136224b";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Adapter adapter;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    List<Articles> articles;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.headlines_recyclerview);
        navigationView = findViewById(R.id.navigation_drawer);
        drawerLayout =  findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.headlines_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.Headlines:
                        Intent intent = new Intent(news_headlines.this,news_headlines.class);
                        startActivity(intent);
                        return true;
                    case R.id.business:
                        Intent intent1 = new Intent(news_headlines.this,business.class);
                        startActivity(intent1);
                        return  true;
                    case R.id.health:
                        Intent intent2 = new Intent(news_headlines.this,health.class);
                        startActivity(intent2);
                        return  true;
                    case R.id.sports:
                        Intent intent3 = new Intent(news_headlines.this,sports.class);
                        startActivity(intent3);
                        return true;
                    case R.id.science:
                        Intent intent4 = new Intent(news_headlines.this,science.class);
                        startActivity(intent4);
                        return true;
                    case R.id.entertainment:
                        Intent intent5 = new Intent(news_headlines.this,entertainment.class);
                        startActivity(intent5);
                        return true;
                    case R.id.technology:
                        Intent intent6 = new Intent(news_headlines.this,technology.class);
                        startActivity(intent6);
                        return true;
                }
                return  false;
            }
        });
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        loadJson();

    }


    public void loadJson(){
        ApiInterface apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        String country = Utils.getCountry();
        Call<Headlines> call;
        call = apiInterface.getHeadlines(country,api_key);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful()&&response.body().getArticles()!=null){
                    articles = response.body().getArticles();
                    adapter = new Adapter(articles,news_headlines.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else{
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
