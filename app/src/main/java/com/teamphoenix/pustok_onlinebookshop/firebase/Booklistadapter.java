package com.teamphoenix.pustok_onlinebookshop.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teamphoenix.pustok_onlinebookshop.MODALs.Boookclass;
import com.teamphoenix.pustok_onlinebookshop.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Booklistadapter extends  RecyclerView.Adapter<Booklistadapter.MyViewholder> {
    ArrayList<Boookclass> blist;
 Context context;

    public Booklistadapter(ArrayList<Boookclass> blist, Context context) {
        this.blist = blist;
        this.context = context;
    }

    @Override
    public MyViewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.writerlist,parent,false);
        return new MyViewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewholder holder, int position) {
        Boookclass boookclass = blist.get(position);
        holder.booknME.setText(boookclass.getBname());
        holder.BOKWR.setText(boookclass.getWrname());
        holder.BOKPRICE.setText(boookclass.getPrice());
        Picasso.get().load(boookclass.getImg()).into(holder.imageView1);
    }

    @Override
    public int getItemCount() {
        return blist.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder{
TextView booknME,BOKWR,BOKPRICE;
ImageView imageView1;
        public MyViewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            booknME=itemView.findViewById(R.id.book_name);
            BOKWR=itemView.findViewById(R.id.book_writer);
            BOKPRICE=itemView.findViewById(R.id.book_price);
imageView1=itemView.findViewById(R.id.book_img);
        }
    }


}
