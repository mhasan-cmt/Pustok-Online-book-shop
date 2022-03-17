package com.teamphoenix.pustok_onlinebookshop.firebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teamphoenix.pustok_onlinebookshop.MODALs.Boookclass;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.bookdetails.BookDetailsActivity;
import com.teamphoenix.pustok_onlinebookshop.entity.Book;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Booklistadapter extends RecyclerView.Adapter<Booklistadapter.MyViewholder> {
    ArrayList<Book> blist;
    Context context;

    public Booklistadapter(ArrayList<Book> blist, Context context) {

        this.blist = blist;
        this.context = context;
    }

    @Override
    public MyViewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.writerlist, parent, false);
        return new MyViewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewholder holder, int position) {
        Book boookclass = blist.get(position);
        holder.booknME.setText(boookclass.getBook_name());
        holder.BOKWR.setText(boookclass.getWriter_name());
        holder.BOKPRICE.setText(boookclass.getPrice());
        Picasso.get().load(boookclass.getBook_image()).into(holder.imageView1);
        holder.single_book_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookDetailsActivity.class);
                intent.putExtra("book_id", boookclass.getBook_id());
                intent.putExtra("book_name",boookclass.getBook_name());
                intent.putExtra("writer_name", boookclass.getWriter_name());
                intent.putExtra("writer_id", boookclass.getWriter_id());
                intent.putExtra("ratings","2");
                intent.putExtra("price",boookclass.getPrice());
                intent.putExtra("image",boookclass.getBook_image());
                intent.putExtra("page_number",boookclass.getPage_number());
                intent.putExtra("language",boookclass.getLanguage());
                intent.putExtra("publisher_id",boookclass.getPublisher_id());
                intent.putExtra("book_type", boookclass.getBook_type());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return blist.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {
        TextView booknME, BOKWR, BOKPRICE;
        ImageView imageView1;
        LinearLayout single_book_container;
        public MyViewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            booknME = itemView.findViewById(R.id.book_name);
            BOKWR = itemView.findViewById(R.id.book_writer);
            BOKPRICE = itemView.findViewById(R.id.book_price);
            imageView1 = itemView.findViewById(R.id.book_img);
            single_book_container = itemView.findViewById(R.id.single_book_container);
        }
    }


}
