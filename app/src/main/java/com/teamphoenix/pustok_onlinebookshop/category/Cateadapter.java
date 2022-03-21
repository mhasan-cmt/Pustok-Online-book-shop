package com.teamphoenix.pustok_onlinebookshop.category;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.teamphoenix.pustok_onlinebookshop.entity.Book;
import com.teamphoenix.pustok_onlinebookshop.entity.CatagModal;
import com.teamphoenix.pustok_onlinebookshop.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Cateadapter extends RecyclerView.Adapter<Cateadapter.MyViewholder> {


    ArrayList<CatagModal> list;
    Context context;

    public Cateadapter(ArrayList<CatagModal> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.catagorylist, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewholder holder, int position) {
        CatagModal dT = list.get(position);
        holder.txt1.setText(dT.getName());
        holder.txt2.setText(dT.getCount());
        Picasso.get().load(dT.getImage()).fit().into(holder.imageView);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Booklist");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Book book = dataSnapshot.getValue(Book.class);
                    if (book.getCategory_id().equals(dT.getId())){
                        i++;
                    }
                }
                holder.txt2.setText(Integer.toString(i)+" টি বই");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.category_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CategoryActivity.class);
                intent.putExtra("category_id",dT.getId());
                intent.putExtra("category_name",dT.getName());
                intent.putExtra("category_count",dT.getCount());
                intent.putExtra("category_img", dT.getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {
        TextView txt1, txt2;
        CardView category_container;
        ImageView imageView;

        public MyViewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.catgename);
            txt2 = itemView.findViewById(R.id.ctaecnt);
            imageView = itemView.findViewById(R.id.cateimge);
            category_container = itemView.findViewById(R.id.category_container);
        }
    }


}
