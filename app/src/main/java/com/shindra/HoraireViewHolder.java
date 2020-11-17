package com.shindra;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HoraireViewHolder extends RecyclerView.ViewHolder {
    public TextView Headline1;
    public TextView Ligne;
    public TextView Time;


    public HoraireViewHolder(@NonNull View itemView) {
        super(itemView);
        Headline1 = itemView.findViewById(R.id.Headline1);
        Ligne = itemView.findViewById(R.id.Ligne);
        Time = itemView.findViewById(R.id.Time);
    }

}
