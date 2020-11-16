package com.shindra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LineAdapter extends RecyclerView.Adapter<LineViewHolder>{

    int pictures[];
    Context context;

    public LineAdapter(Context ct, int pictures[]){
        context = ct;
        this.pictures = pictures;
    }

    @NonNull
    @Override
    public LineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview, parent, false);
        return new LineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LineViewHolder holder, int position) {
        holder.onBind(pictures[position]);
    }

    @Override
    public int getItemCount() {
        return pictures.length;
    }
}
