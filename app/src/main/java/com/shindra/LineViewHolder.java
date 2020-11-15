package com.shindra;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;


public class LineViewHolder extends RecyclerView.ViewHolder {

    public LineViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onBind(Line line){
        //lineName.setText(line.na);
    }

}
