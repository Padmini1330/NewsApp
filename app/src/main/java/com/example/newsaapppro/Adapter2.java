package com.example.newsaapppro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.newsaapppro.fields.health_content;
import com.example.newsaapppro.health_news.h_articles;

import java.util.List;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder> {
    Context context;
    List<h_articles> h_articles;

    public Adapter2(Context context, List<h_articles> h_articlesList) {
        this.context = context;
        this.h_articles = h_articlesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.h_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        h_articles data = h_articles.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();
        holder.author.setText(data.getAuthor());
        holder.desc.setText(data.getDescription());
        holder.title.setText(data.getTitle());
        final String newUrl = data.getUrl();
        String mdate ;
        mdate = data.getPublishedAt().toString();
        String res =mdate.substring(0,10);
        holder.date.setText(res);
        String url = data.getUrlToImage();
        Glide.with(context)
                .load(data.getUrlToImage())
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, health_content.class);

                intent.putExtra("Url",newUrl);

                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return h_articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView author,title,date,desc;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.h_author);
            title = itemView.findViewById(R.id.h_title);
            date  = itemView.findViewById(R.id.h_date);
            desc = itemView.findViewById(R.id.h_description);
            imageView = itemView.findViewById(R.id.h_image);
            cardView = itemView.findViewById(R.id.h_card);
        }
    }
}
