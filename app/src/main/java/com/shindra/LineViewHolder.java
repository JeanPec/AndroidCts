package com.shindra;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

public class LineViewHolder extends RecyclerView.ViewHolder {

    private ImageView picture;
    private Button button;

    public LineViewHolder(@NonNull View itemView) {
        super(itemView);
        picture = itemView.findViewById(R.id.Subway_number);
        button = itemView.findViewById(R.id.Button);
    }

    public void onBind(Line lines, LineClick callback){

        this.picture.setImageResource(LineToPicture(lines));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onLineClick(lines);
            }
        });

    }

    public int LineToPicture(Line lines) {
        String name = lines.getName();
        switch (name) {
            case "A":
                return R.drawable.tram_a;
            case "B":
                return R.drawable.tram_b;
            case "C":
                return R.drawable.tram_c;
            case "D":
                return R.drawable.tram_d;
            case "E":
                return R.drawable.tram_e;
            case "F":
                return R.drawable.tram_f;
            default:
                return R.drawable.tram;
        }
    }
}