package com.shindra;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TramViewHolder extends RecyclerView.ViewHolder {

    ImageView img1,img2;

    public TramViewHolder(@NonNull View iView){
        super(iView);
        img1 = iView.findViewById(R.id.imageView3);
        img2 = iView.findViewById(R.id.imageView4);
    }

}
