package com.example.channelprotest_mvc.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.channelprotest_mvc.R;
import com.example.channelprotest_mvc.room.Inbox;
import com.example.channelprotest_mvc.room.News;

import java.util.ArrayList;
import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.NewsHolder> {


    private List<Inbox> inboxes = new ArrayList<>();
    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inbox,parent,false);



        return new NewsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {

        Inbox currentInbox = inboxes.get(position);
        holder.textViewTitle.setText(currentInbox.getTitle());
        holder.textViewDescription.setText(currentInbox.getDescription());

    }

    @Override
    public int getItemCount() {
        return inboxes.size();
    }

    public void setInboxes(List<Inbox> inboxes)
    {
        this.inboxes = inboxes;
        notifyDataSetChanged();
    }

    class NewsHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitle;
        private TextView textViewDescription;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.title_);
            textViewDescription = itemView.findViewById(R.id.desc_);

        }
    }
}
