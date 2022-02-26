package com.teamphoenix.pustok_onlinebookshop.bookdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamphoenix.pustok_onlinebookshop.R;

public class BookDetailAdapter extends RecyclerView.Adapter<BookDetailViewHolder> {
    Context context;
    String[] bookNameList;
    int[] bookImgList;
    int[] bookReviewList;
    int[] bookRatingList;

    public BookDetailAdapter(Context context, String[] bookNameList, int[] bookImgList, int[] bookReviewList, int[] bookRatingList) {
        this.context = context;
        this.bookNameList = bookNameList;
        this.bookImgList = bookImgList;
        this.bookReviewList = bookReviewList;
        this.bookRatingList = bookRatingList;
    }

    @NonNull
    @Override
    public BookDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookDetailViewHolder(LayoutInflater.from(context).inflate(R.layout.related_book_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookDetailViewHolder holder, int position) {
        holder.related_book_name.setText(bookNameList[position]);
        holder.related_writer_name.setText("কঙ্কর আহসান");
        holder.related_book_img.setImageResource(bookImgList[position]);
        holder.related_rating.setRating(bookRatingList[position]);
        holder.related_total_reviews.setText("("+Integer.toString(bookReviewList[position])+")");
    }

    @Override
    public int getItemCount() {
        return bookNameList.length;
    }
}
class BookDetailViewHolder extends RecyclerView.ViewHolder{
    TextView related_book_name,related_writer_name,related_total_reviews;
    ImageView related_book_img;
    RatingBar related_rating;
    public BookDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        related_book_name=itemView.findViewById(R.id.related_book_name);
        related_writer_name=itemView.findViewById(R.id.related_writer_name);
        related_book_img=itemView.findViewById(R.id.related_book_img);
        related_rating=itemView.findViewById(R.id.related_rating);
        related_total_reviews=itemView.findViewById(R.id.related_total_reviews);
    }
}
