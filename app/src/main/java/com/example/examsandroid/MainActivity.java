package com.example.examsandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Article> articlesList;
    private JsonApi jsonPlaceHolderApi;
    private Retrofit retrofit = new
            Retrofit.Builder().baseUrl("https://newsapi.org/v2/").addConverterFactory(GsonConverterFactory.create()).build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsonPlaceHolderApi = retrofit.create(JsonApi.class);
        articlesList = new ArrayList<>();
        getArticles();
    }

    private void getArticles() {
        Call<Bracket> call = jsonPlaceHolderApi.getArticles();
        call.enqueue(new Callback<Bracket>() {
            @Override
            public void onResponse(Call<Bracket> call, Response<Bracket> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this,"response "+response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Bracket container = response.body();
                List<Article> articles = container.getArticles();

                for (int i=0; i<articles.size(); i++){
                    articlesList.add(articles.get(i));
                }
                buildRecyclerView();



            }

            @Override
            public void onFailure(Call<Bracket> call, Throwable t) {
                //Toast.makeText(getActivity(),"t: "+t.getMessage(),Toast.LENGTH_SHORT).show();-
            }
        });
    }



    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ArticleAdapter(articlesList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }


}






