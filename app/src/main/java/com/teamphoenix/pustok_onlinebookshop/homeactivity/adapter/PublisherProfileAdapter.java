package com.teamphoenix.pustok_onlinebookshop.homeactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.entity.Publisher;

import java.util.ArrayList;

public class PublisherProfileAdapter extends RecyclerView.Adapter<PublisherProfileAdapter.MyHolder> {

    Context context;
    ArrayList<Publisher> publisherDataArray;
    ArrayList<Integer> images = new ArrayList<>();

    public PublisherProfileAdapter(Context context, ArrayList<Publisher> userDataArray) {
        this.context = context;
        this.publisherDataArray = userDataArray;
        images.add(R.drawable.writer);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_publisher,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
//        holder.publisher_profile_pic.setImageResource(images.get(position));
        Glide.with(context).asBitmap().load(publisherDataArray.get(position).getPublisher_img())
                .into(holder.publisher_profile_pic);
        holder.publisher_name.setText(publisherDataArray.get(position).getPublisher_name());
        holder.publisher_total_book.setText(publisherDataArray.get(position).getTotal_books());
    }

    @Override
    public int getItemCount() {
        return publisherDataArray.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView publisher_profile_pic;
        TextView publisher_name, publisher_total_book;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            publisher_profile_pic = itemView.findViewById(R.id.publisher_profile_pic);
            publisher_name = itemView.findViewById(R.id.publisher_name);
            publisher_total_book = itemView.findViewById(R.id.publisher_total_book);
        }
    }
}
