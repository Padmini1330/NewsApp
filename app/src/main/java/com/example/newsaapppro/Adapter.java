package com.example.newsaapppro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsaapppro.headlines.Articles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    List<Articles> articles = new ArrayList<>();
    private ViewHolder holder;
    private int position;

    public Adapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.carditems,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Articles data = articles.get(position);
       holder.title.setText(data.getTitle());
       holder.author.setText(data.getAuthor());
       holder.desc.setText(data.getDescription());
       String date = data.getPublishedAt();
       String newdate = date.substring(0,10);
       holder.date.setText(newdate);
       Picasso.with(context).load(data.getUrlToImage()).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date,author,desc,title;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date  = itemView.findViewById(R.id.date);
            author = itemView.findViewById(R.id.author);
            desc = itemView.findViewById(R.id.description);
            title = itemView.findViewById(R.id.headlines_title);
            imageView = itemView.findViewById(R.id.headlines_image);
            cardView = itemView.findViewById(R.id.newscard);
        }
    }
}
