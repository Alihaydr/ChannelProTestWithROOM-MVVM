package com.example.channelprotest_mvc.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.channelprotest_mvc.R;
import com.example.channelprotest_mvc.room.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {


    private List<News> news = new ArrayList<>();
    private Context context;
    public NewsAdapter(Context context){

        this.context=context;

    }
    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news,parent,false);



        return new NewsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {

        News currentNews = news.get(position);
        holder.textViewTitle.setText(currentNews.getTitle());
        holder.textViewDescription.setText(currentNews.getDescription());
        holder.imageView.setImageDrawable(context.getResources().getDrawable(currentNews.getImage()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+currentNews.getSite()));
                context.startActivity(browserIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public void setNews(List<News> news)
    {
        this.news = news;
        notifyDataSetChanged();
    }

    class NewsHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitle;
        private TextView textViewDescription;
        private ImageView imageView;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.brand_item);
            textViewDescription = itemView.findViewById(R.id.title_item);
            imageView = itemView.findViewById(R.id.image_item_news);

        }
    }
}
