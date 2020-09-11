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
import com.example.newsaapppro.fields.business_content;
import com.example.newsaapppro.business_news.b_articles;

import java.util.List;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder> {
    Context context;
    List<b_articles> b_articles;

    public Adapter1(Context context, List<b_articles> b_articlesList) {
        this.context = context;
        this.b_articles = b_articlesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.b_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        b_articles data = b_articles.get(position);
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
                Intent intent = new Intent(context, business_content.class);

                intent.putExtra("Url",newUrl);

                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return b_articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView author,title,date,desc;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.b_author);
            title = itemView.findViewById(R.id.b_title);
            date  = itemView.findViewById(R.id.b_date);
            desc = itemView.findViewById(R.id.b_description);
            imageView = itemView.findViewById(R.id.b_image);
            cardView = itemView.findViewById(R.id.b_card);
        }
    }
}
