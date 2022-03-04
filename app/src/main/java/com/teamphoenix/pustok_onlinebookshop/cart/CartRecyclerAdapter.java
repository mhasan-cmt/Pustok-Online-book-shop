package com.teamphoenix.pustok_onlinebookshop.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamphoenix.pustok_onlinebookshop.R;

import java.util.ArrayList;
import java.util.List;

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartViewHolder> {

    Context context;
    ArrayList<CartModel> cartModelList;

    public CartRecyclerAdapter(Context context, ArrayList<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.cart_img.setImageResource(cartModelList.get(position).getImage());
        holder.cart_book_writer.setText(cartModelList.get(position).getWriterName());
        holder.cart_book_name.setText(cartModelList.get(position).getBookName());
        holder.cart_book_price.setText(cartModelList.get(position).getPrice());
        holder.cart_ratings.setRating(Float.parseFloat(cartModelList.get(position).getRatings()));
        holder.cart_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Deleted...!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }
}
class CartViewHolder extends RecyclerView.ViewHolder{

    public ImageView cart_img;
    public TextView cart_book_name,cart_book_writer,cart_book_price;
    public RatingBar cart_ratings;
    public ImageButton cart_delete_btn;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        cart_img = itemView.findViewById(R.id.cart_img);
        cart_book_name = itemView.findViewById(R.id.cart_book_name);
        cart_book_writer = itemView.findViewById(R.id.cart_book_writer);
        cart_book_price = itemView.findViewById(R.id.cart_book_price);
        cart_ratings = itemView.findViewById(R.id.cart_ratings);
        cart_delete_btn = itemView.findViewById(R.id.cart_delete_btn);
    }
}