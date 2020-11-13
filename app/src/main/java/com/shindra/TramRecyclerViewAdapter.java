package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TramRecyclerViewAdapter extends RecyclerView.Adapter<TramViewHolder>{

    private ArrayList<Integer> tramList;

    TramRecyclerViewAdapter(ArrayList<Integer> tramList){
        this.tramList = tramList;
    }

    @NonNull
    @Override
    public TramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tramView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tram_card_view,parent,false);
        return new TramViewHolder(tramView);
    }

    @Override
    public void onBindViewHolder(@NonNull TramViewHolder holder, int position) {
        holder.onBind(tramList.get(position));
    }

    @Override
    public int getItemCount() {
        return tramList.size();
    }
}
