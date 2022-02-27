package com.teamphoenix.pustok_onlinebookshop.homeactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamphoenix.pustok_onlinebookshop.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProfileRecyclerViewAdapter extends RecyclerView.Adapter<ProfileViewHolder> {

    ArrayList<String> texts;
    int[] icons;

    public ProfileRecyclerViewAdapter(ArrayList<String> texts, int[] icons) {
        this.texts = texts;
        this.icons = icons;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ProfileViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proflie,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProfileViewHolder holder, int position) {
        holder.textView.setText(texts.get(position).toString().trim());
        holder.imageView.setImageResource(icons[position]);
    }

    @Override
    public int getItemCount() {
        return texts.size();
    }
}
