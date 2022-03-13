package com.teamphoenix.pustok_onlinebookshop.homeactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamphoenix.pustok_onlinebookshop.R;

public class BookSelfAdapter extends RecyclerView.Adapter<BookSelfAdapter.bookSelfViewHolder> {
    Context context;
    String[] bookName;
    int[] bookImage;

    public BookSelfAdapter(Context context, String[] bookName, int[] bookImage) {
        this.context=context;
        this.bookName = bookName;
        this.bookImage = bookImage;
    }

    @NonNull
    @Override
    public bookSelfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_self_recyclerview,parent,false);
        return new bookSelfViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bookSelfViewHolder holder, int position) {
        holder.textView.setText(bookName[position]);
        holder.imageView.setImageResource(bookImage[position]);
    }

    @Override
    public int getItemCount() {
        return bookImage.length;
    }

    public class bookSelfViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public bookSelfViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);
        }
    }
}
