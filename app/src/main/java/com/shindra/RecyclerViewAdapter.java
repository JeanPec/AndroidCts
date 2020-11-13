package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<TramViewHolder> {

    private ArrayList<Tram> trams;

    RecyclerViewAdapter(ArrayList<Tram> trams){
        this.trams = trams;
    }


    @NonNull
    @Override
    public TramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tramsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tramlayout,parent,false);

        return new TramViewHolder(tramsView);
    }

    @Override
    public void onBindViewHolder(@NonNull TramViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return trams.size;
    }
}
