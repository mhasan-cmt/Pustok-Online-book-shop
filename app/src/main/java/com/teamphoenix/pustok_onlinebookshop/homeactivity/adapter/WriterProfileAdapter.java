package com.teamphoenix.pustok_onlinebookshop.homeactivity.adapter;

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

import com.squareup.picasso.Picasso;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.writer_profile.writer_prof;
import com.teamphoenix.pustok_onlinebookshop.entity.Writer;

import java.util.ArrayList;

public class  WriterProfileAdapter extends RecyclerView.Adapter<WriterProfileAdapter.MyHolder> {

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
        holder.writer_profile_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, writer_prof.class);

                intent.putExtra("writer_id", writerArrayList.get(holder.getAdapterPosition()).getWriter_id());
                intent.putExtra("writer_name", writerArrayList.get(holder.getAdapterPosition()).getWriter_name());
                intent.putExtra("writer_description", writerArrayList.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("writer_img", writerArrayList.get(holder.getAdapterPosition()).getProfile_pic());
                intent.putExtra("writer_follower", writerArrayList.get(holder.getAdapterPosition()).getFollowers());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return writerArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView publisher_profile_pic;
        TextView publisher_name, publisher_total_book;
        CardView writer_profile_container;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            publisher_profile_pic = itemView.findViewById(R.id.publisher_profile_pic);
            publisher_name = itemView.findViewById(R.id.publisher_name);
            publisher_total_book = itemView.findViewById(R.id.publisher_total_book);
            writer_profile_container = itemView.findViewById(R.id.writer_profile_container);
        }
    }
}
