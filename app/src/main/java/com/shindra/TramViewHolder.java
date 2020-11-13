package com.shindra;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TramViewHolder extends RecyclerView.ViewHolder{

    private final ImageView tramText;

    public TramViewHolder(@NonNull View itemView) {
        super(itemView);
        tramText = itemView.findViewById(R.id.tram_text);
    }

    public void onBind(Integer line){
        tramText.setImageResource(line);
    }
}
