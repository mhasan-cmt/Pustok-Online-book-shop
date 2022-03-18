package com.teamphoenix.pustok_onlinebookshop.bookdetails;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.entity.Book;
import com.teamphoenix.pustok_onlinebookshop.entity.Writer;

import java.util.ArrayList;

public class BookDetailAdapter extends RecyclerView.Adapter<BookDetailViewHolder> {
    Context context;
    ArrayList<Book> bookArrayList;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("writers");

    Writer writer;

    public BookDetailAdapter(Context context, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
    }

    @NonNull
    @Override
    public BookDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookDetailViewHolder(LayoutInflater.from(context).inflate(R.layout.related_book_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookDetailViewHolder holder, int position) {

        holder.relatedBookLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookDetailsActivity.class);
                intent.putExtra("book_id", bookArrayList.get(position).getBook_id());
                intent.putExtra("book_name",bookArrayList.get(position).getBook_name());
                intent.putExtra("writer_name", holder.related_writer_name.getText());
                intent.putExtra("writer_id", bookArrayList.get(position).getWriter_id());
                intent.putExtra("ratings","2");
                intent.putExtra("price",bookArrayList.get(position).getPrice());
                intent.putExtra("image",bookArrayList.get(position).getBook_image());
                intent.putExtra("category_id",bookArrayList.get(position).getCategory_id());
                intent.putExtra("page_number",bookArrayList.get(position).getPage_number());
                intent.putExtra("language",bookArrayList.get(position).getLanguage());
                intent.putExtra("publisher_id",bookArrayList.get(position).getPublisher_id());
                intent.putExtra("book_type", bookArrayList.get(position).getBook_type());
                context.startActivity(intent);
            }
        });

        holder.related_book_name.setText(bookArrayList.get(position).getBook_name());

        reference.child(bookArrayList.get(position).getWriter_id()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                writer = snapshot.getValue(Writer.class);
                holder.related_writer_name.setText(writer.getWriter_name());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Picasso.get().load(bookArrayList.get(position).getBook_image()).into(holder.related_book_img);
        holder.related_rating.setRating(4);
        holder.related_total_reviews.setText("12");
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }
}
class BookDetailViewHolder extends RecyclerView.ViewHolder{
    TextView related_book_name,related_writer_name,related_total_reviews;
    ImageView related_book_img;
    RatingBar related_rating;
    LinearLayout relatedBookLayout;
    public BookDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        related_book_name=itemView.findViewById(R.id.related_book_name);
        related_writer_name=itemView.findViewById(R.id.related_writer_name);
        related_book_img=itemView.findViewById(R.id.related_book_img);
        related_rating=itemView.findViewById(R.id.related_rating);
        related_total_reviews=itemView.findViewById(R.id.related_total_reviews);
        relatedBookLayout=itemView.findViewById(R.id.relatedBookLayout);
    }
}
