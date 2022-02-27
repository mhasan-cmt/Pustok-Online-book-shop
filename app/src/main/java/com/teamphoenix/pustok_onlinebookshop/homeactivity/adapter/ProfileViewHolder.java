package com.teamphoenix.pustok_onlinebookshop.homeactivity.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamphoenix.pustok_onlinebookshop.R;

import org.jetbrains.annotations.NotNull;

public class ProfileViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public ImageView imageView;
    public ProfileViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.profile_first_text_view);
        imageView = itemView.findViewById(R.id.profile_first_icon);
    }
}
