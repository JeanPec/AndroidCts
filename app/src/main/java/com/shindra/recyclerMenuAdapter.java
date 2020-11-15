package com.shindra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyclerMenuAdapter extends RecyclerView.Adapter<recyclerMenuAdapter.MyViewHolder> {

    List<TramLine> Lines;
    Context context;

    public recyclerMenuAdapter(Context context1, List<TramLine> Lines1) {
        context = context1;
        Lines = Lines1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ImgTram, IconLineTram;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ImgTram = itemView.findViewById(R.id.tramImg_onLayout);
            IconLineTram = itemView.findViewById(R.id.tramLineImg_onLayout);
        }
    }


    @NonNull
    @Override
    public recyclerMenuAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View MyMainMenuView = inflater.inflate(R.layout.custom_menu, parent, false);
        return new MyViewHolder(MyMainMenuView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerMenuAdapter.MyViewHolder holder, int position) {
       TramLine Line = Lines.get(position);
        holder.IconLineTram.setImageResource(Line.getIcon());
        holder.ImgTram.setImageResource(Line.getTramImg());
    }

    @Override
    public int getItemCount() {
        return Lines.size();
    }

}