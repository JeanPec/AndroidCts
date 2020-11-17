package com.shindra.dummy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;

import java.util.ArrayList;

public class tramAdapter extends RecyclerView.Adapter<TramCard>{
    private ArrayList<Tram> trams;


    public tramAdapter(ArrayList<Tram> trams) {
        this.trams = trams;
    }

    @NonNull
    @Override
    public TramCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tramView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main, parent,  false);

        return new TramCard(tramView);
    }

    @Override
    public void onBindViewHolder(@NonNull TramCard holder, int position) {
        holder.onBind(trams.get(position));
    }

    @Override
    public int getItemCount() {
        return trams.size();
    }
}
