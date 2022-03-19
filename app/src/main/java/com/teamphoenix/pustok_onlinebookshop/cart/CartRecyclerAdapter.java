package com.teamphoenix.pustok_onlinebookshop.cart;

import android.annotation.SuppressLint;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.entity.Book;
import com.teamphoenix.pustok_onlinebookshop.entity.Cart;
import com.teamphoenix.pustok_onlinebookshop.entity.Writer;

import java.util.ArrayList;

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartViewHolder> {

    Context context;
    ArrayList<Cart> cartModelList;

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("cart");
    DatabaseReference bookReference = FirebaseDatabase.getInstance().getReference("Booklist");
    DatabaseReference writerReference = FirebaseDatabase.getInstance().getReference("writers");

    public CartRecyclerAdapter(Context context, ArrayList<Cart> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.cart_img.setImageResource(cartModelList.get(position).getImage());
        bookReference.addValueEventListener(new ValueEventListener() {
            int pos = position;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Book book = dataSnapshot.getValue(Book.class);
                    if (book.getBook_id().equals(cartModelList.get(pos).getBook_id())) {
                        Glide.with(context).asBitmap().load(book.getBook_image()).into(holder.cart_img);
                        holder.cart_book_name.setText(book.getBook_name());
                        writerReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                                    Writer writer = dataSnapshot1.getValue(Writer.class);
                                    if (book.getWriter_id().equals(writer.getWriter_id())) {
                                        holder.cart_book_writer.setText(writer.getWriter_name());
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.cart_book_price.setText(Float.toString(Float.parseFloat(cartModelList.get(position).getTotalPrice())));
//        holder.cart_ratings.setRating(Float.parseFloat(cartModelList.get(position).getRatings()));
        holder.cart_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Deleted...!", Toast.LENGTH_SHORT).show();
                reference.child(cartModelList.get(holder.getAdapterPosition()).getCart_id()).removeValue();
            }
        });
        holder.cart_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(holder.cart_quantity.getText().toString());
                quantity++;
                holder.cart_book_price.setText(Float.toString(Float.parseFloat((String) cartModelList.get(position).getTotalPrice()) * quantity));
                holder.cart_quantity.setText("" + quantity);
            }
        });
        holder.cart_remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(holder.cart_quantity.getText().toString());
                if (quantity > 1) {
                    quantity--;
                    holder.cart_book_price.setText(Float.toString(Float.parseFloat((String) cartModelList.get(position).getTotalPrice()) * quantity));
                    holder.cart_quantity.setText("" + quantity);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }
}

class CartViewHolder extends RecyclerView.ViewHolder {

    public ImageView cart_img;
    public TextView cart_book_name, cart_book_writer, cart_book_price, cart_quantity;
    public RatingBar cart_ratings;
    public ImageButton cart_delete_btn;
    public ImageButton cart_remove_btn, cart_add_btn;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        cart_img = itemView.findViewById(R.id.cart_img);
        cart_book_name = itemView.findViewById(R.id.cart_book_name);
        cart_book_writer = itemView.findViewById(R.id.cart_book_writer);
        cart_book_price = itemView.findViewById(R.id.cart_book_price);
        cart_ratings = itemView.findViewById(R.id.cart_ratings);
        cart_delete_btn = itemView.findViewById(R.id.cart_delete_btn);
        cart_quantity = itemView.findViewById(R.id.cart_quantity);
        cart_add_btn = itemView.findViewById(R.id.cart_add_btn);
        cart_remove_btn = itemView.findViewById(R.id.cart_remove_btn);
    }
}
