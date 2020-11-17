package com.shindra;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TramViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivTextTram;

    public TramViewHolder(@NonNull View view){
        super(view);
        ivTextTram = view.findViewById(R.id.imageView3);
    }

    public void onBind(Integer iLine) {
        ivTextTram.setImageResource(iLine);
    }
}
