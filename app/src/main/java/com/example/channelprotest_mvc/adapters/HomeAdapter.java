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
import com.example.channelprotest_mvc.room.Home;
import com.example.channelprotest_mvc.room.News;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.NewsHolder> {


    private List<Home> homes = new ArrayList<>();
    private Context context;
    public HomeAdapter (Context context){

        this.context=context;

    }
    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,parent,false);



        return new NewsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {

        Home currentNews = homes.get(position);
        holder.textViewTitle.setText(currentNews.getTitle());
        holder.textViewDescription.setText(currentNews.getDescription());
        holder.imageView.setImageDrawable(context.getResources().getDrawable(currentNews.getImage()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+currentNews.getTitle()));
                context.startActivity(browserIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return homes.size();
    }

    public void setHomes(List<Home> homes)
    {
        this.homes = homes;
        notifyDataSetChanged();
    }

    class NewsHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitle;
        private TextView textViewDescription;
        private ImageView imageView;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.site_item);
            textViewDescription = itemView.findViewById(R.id.textview);
            imageView = itemView.findViewById(R.id.image_item);

        }
    }
}
