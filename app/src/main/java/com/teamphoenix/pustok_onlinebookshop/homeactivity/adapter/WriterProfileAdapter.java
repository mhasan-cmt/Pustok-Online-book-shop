package com.teamphoenix.pustok_onlinebookshop.homeactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.entity.Writer;

import java.util.ArrayList;

public class WriterProfileAdapter extends RecyclerView.Adapter<WriterProfileAdapter.MyHolder> {

    Context context;
    ArrayList<Writer> writerArrayList;


    public WriterProfileAdapter(Context context, ArrayList<Writer> userDataArray) {
        this.context = context;
        this.writerArrayList = userDataArray;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_publisher, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.publisher_name.setText(writerArrayList.get(position).getWriter_name());
        holder.publisher_total_book.setText(writerArrayList.get(position).getFollowers());
        Picasso.get().load(writerArrayList.get(position).getProfile_pic()).into(holder.publisher_profile_pic);
    }

    @Override
    public int getItemCount() {
        return writerArrayList.size();
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
