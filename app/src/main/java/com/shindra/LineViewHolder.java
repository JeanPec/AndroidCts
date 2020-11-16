package com.shindra;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LineViewHolder extends RecyclerView.ViewHolder {

    private final ImageView MyPicture;

    public LineViewHolder(@NonNull View itemView) {
        super(itemView);
        MyPicture = itemView.findViewById(R.id.Subway_number);
    }

    public void onBind(Integer picture){
        MyPicture.setImageResource(picture);
    }
}