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


    private ArrayList<Line> lines;
    private LineClick callback;

    public LineAdapter(ArrayList<Line> lines, LineClick callback){
        this.lines = lines;
        this.callback = callback;
    }

    @NonNull
    @Override
    public LineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lineview, parent, false);
        return new LineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LineViewHolder holder, int position) {
        Line lines = this.lines.get(position);
        holder.onBind(lines, callback);
    }

    @Override
    public int getItemCount() {
        return lines.size();
    }
}
