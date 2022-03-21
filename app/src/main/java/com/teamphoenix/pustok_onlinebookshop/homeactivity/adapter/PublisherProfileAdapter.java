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

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamphoenix.pustok_onlinebookshop.Publisher_Profile.Publisher_Profile;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.entity.Book;
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
        Publisher publisher = publisherDataArray.get(position);
        Glide.with(context).asBitmap().load(publisherDataArray.get(position).getPublisher_img())
                .into(holder.publisher_profile_pic);
        holder.publisher_name.setText(publisherDataArray.get(position).getPublisher_name());
        holder.publisher_total_book.setText(publisherDataArray.get(position).getTotal_books());
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Booklist");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Book book = dataSnapshot.getValue(Book.class);
                    if (book.getPublisher_id().equals(publisher.getPublisher_id())){
                        i++;
                    }
                }
                holder.publisher_total_book.setText(Integer.toString(i)+" টি বই");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.writer_profile_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Publisher_Profile.class);
                intent.putExtra("publisher_id", publisherDataArray.get(holder.getAdapterPosition()).getPublisher_id());
                intent.putExtra("publisher_name", publisherDataArray.get(holder.getAdapterPosition()).getPublisher_name());
                intent.putExtra("publisher_profile_pic", publisherDataArray.get(position).getPublisher_img());
                intent.putExtra("publisher_total_book", holder.publisher_total_book.getText());
                intent.putExtra("publisher_follower", publisherDataArray.get(holder.getAdapterPosition()).getTotal_followers());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return publisherDataArray.size();
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
