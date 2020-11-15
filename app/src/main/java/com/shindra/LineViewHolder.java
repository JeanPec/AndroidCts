package com.shindra;

import android.view.View;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.Objects;

public class LineViewHolder extends RecyclerView.ViewHolder {

    private final ImageView lineName;

    public LineViewHolder(@NonNull View itemView){
        super(itemView);
        lineName = itemView.findViewById(R.id.logo_ligne);
    }
    public void onBind(Line ligne){
        switch(ligne.getName()) {
            case "A":
                lineName.setImageResource(R.drawable.ic_tram_a);
                break;
            case "B":
                lineName.setImageResource(R.drawable.ic_tram_b);
                break;
            case "C":
                lineName.setImageResource(R.drawable.ic_tram_c);
                break;
            case "D":
                lineName.setImageResource(R.drawable.ic_tram_d);
                break;
            case "E":
                lineName.setImageResource(R.drawable.ic_tram_e);
                break;
            case "F":
                lineName.setImageResource(R.drawable.ic_tram_f);
                break;
            default:
                lineName.setImageResource(R.drawable.ic_tram);
        }
        // TODO modifier switch cqse qvec bon nom de ligne
    }
}
