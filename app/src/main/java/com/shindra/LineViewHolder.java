package com.shindra;

import android.widget.ImageView;

import androidx.annotation.NonNull;

public class LineViewHolder extends RecyclerView.ViewHolder {

    private final ImageView lineName;

    public LineViewHolder(@NonNull View itemView){
        super(itemView);
    }
    public void onBind(Ligne ligne){
        lineName = itemView.findViewById(R.id.ic_a);
    }
//TODO finir le view holder
}
