package com.example.examsandroid;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {

    @GET("top-headlines?country=gr&category=science&apiKey=93451f9688784c69b3bdb930fea0c8c0")
    Call<Bracket> getArticles();
}
