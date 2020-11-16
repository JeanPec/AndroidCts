package com.shindra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class LineAdapter extends RecyclerView.Adapter<LineViewHolder>{

    //int pictures[];
    ArrayList<Line> lines;

    public LineAdapter(ArrayList<Line> lines){
        //this.pictures = pictures;
        this.lines = lines;
    }

    @NonNull
    @Override
    public LineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lineview, parent, false);
        return new LineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LineViewHolder holder, int position) {
        //holder.onBind(pictures[position]);
        Line lines = this.lines.get(position);
        holder.onBind(lines);
    }

    @Override
    public int getItemCount() {
        //return pictures.length;
        return lines.size();
    }
}
