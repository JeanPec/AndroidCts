package com.shindra;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<LineViewHolder> {
    private ArrayList<Line>lines;
    RecyclerViewAdapter(ArrayList<Line> lines){
        this.lines = lines;
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
        holder.onBind(lines.get(position));
    }

    @Override
    public int getItemCount(){
        return lines.size();
    }

}
