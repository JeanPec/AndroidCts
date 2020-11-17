package com.shindra;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<LineViewHolder> {
    private final RecyclerButtonClick callBack;
    private ArrayList<Line>lines;
    RecyclerViewAdapter(ArrayList<Line> lines, RecyclerButtonClick callBack){

        this.lines = lines;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public LineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View lineView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.line_recycler_card_view, parent, false);
        return new LineViewHolder(lineView);
    }

    @Override
    public void onBindViewHolder(@NonNull LineViewHolder holder, int position){
        holder.onBind(lines.get(position), callBack);
    }

    @Override
    public int getItemCount(){
        return lines.size();
    }

}
