package com.shindra;

import android.widget.ImageView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TramsViewHolder extends RecyclerView.ViewHolder
{
    private final ImageView imageLigneTram;

    public TramsViewHolder(@NonNull View itemView)
    {
        super(itemView);
        imageLigneTram = itemView.findViewById(R.id.imageLigneTram);
    }

    public void onBind(Integer image)
    {
        imageLigneTram.setImageResource(image);
    }
}
