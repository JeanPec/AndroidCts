package com.shindra;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

public class LineViewHolder extends RecyclerView.ViewHolder {

    private final ImageView lineName;
    public final Button button;

    public LineViewHolder(@NonNull View itemView){
        super(itemView);
        lineName = itemView.findViewById(R.id.logo_ligne);
        button = itemView.findViewById(R.id.schedule_button);
    }
    public void onBind(Line line, RecyclerButtonClick callback){
        switch(line.getName()) {
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
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                callback.onLineClick(line);
            }
        });
        // TODO modifier switch case avec bon nom de ligne
    }
}
