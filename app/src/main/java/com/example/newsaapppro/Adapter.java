package com.example.newsaapppro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.newsaapppro.headlines.Articles;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Articles> articles;
    Context context;
    OnItemClickListener onItemClickListener;

    public Adapter(List<Articles> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carditems,parent,false);
        return new ViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ViewHolder viewHolder = holder;
        Articles data = articles.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();
        holder.title.setText(data.getTitle());
        final String newUrl = data.getUrl();
        String mdate ;
        mdate = data.getPublishedAt().toString();
        String res =mdate.substring(0,10);
        holder.date.setText(res);
        holder.author.setText(data.getAuthor());
        holder.desc.setText(data.getDescription());
        holder.source.setText(data.getSource().getName());
        Glide.with(context)
                .load(data.getUrlToImage())
                .apply(requestOptions)
              .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,news_headlines.class);

                intent.putExtra("Url",newUrl);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener{
        void OnItemClick(View view,int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView author,title,source,date,desc;
        ImageView imageView;
        ProgressBar bar;
        CardView cardView;
        OnItemClickListener onItemClickListener;
        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            title = itemView.findViewById(R.id.title);
            date  = itemView.findViewById(R.id.date);
            desc = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.headlines_image);
            cardView = itemView.findViewById(R.id.newscard);
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.OnItemClick(view,getAdapterPosition());
        }
    }


}





