package com.example.newsaapppro.health_news;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private String Url_base  = "https://newsapi.org/v2/";
    Retrofit retrofit;
    static ApiClient apiClient;

  private ApiClient(){
      retrofit = new Retrofit.Builder().baseUrl(Url_base)
              .addConverterFactory(GsonConverterFactory.create()).build();
  }
    public static synchronized com.example.newsaapppro.health_news.ApiClient getInstance(){
        if(apiClient==null){
            apiClient = new ApiClient();
        }
        return apiClient;
    }
    public com.example.newsaapppro.health_news.ApiInterface getApi(){
        return  retrofit.create(ApiInterface.class);
    }
}
