package com.example.examsandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examsandroid.Article;
import com.example.examsandroid.R;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ExampleViewHolder> {
    private List<Article> mExampleList;




    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView title,name,des;
        public ImageView image;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_title);
            name = itemView.findViewById(R.id.textView_name);
            des = itemView.findViewById(R.id.textView_des);
            image = itemView.findViewById(R.id.image);


        }
    }

    public ArticleAdapter(List<Article> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_layout, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Article currentItem = mExampleList.get(position);
        holder.title.setText(currentItem.getTitle());
        holder.name.setText("By "+currentItem.getSource().getName());
        holder.des.setText(currentItem.getDescription());
        Picasso.get().load(currentItem.getUrlToImage()).into(holder.image);



    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

}