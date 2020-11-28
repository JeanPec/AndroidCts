package com.shindra.dummy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;

import java.util.ArrayList;

public class tramAdapter extends RecyclerView.Adapter<Holder>{
    private ArrayList<Tram> trams;
    Holder.onButtonClickListener callback;


    public tramAdapter(ArrayList<Tram> trams, Holder.onButtonClickListener callback) {
        this.trams = trams;
        this.callback = callback;
    }

    public void setTrams(ArrayList<Tram> trams) {
        this.trams = trams;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tramView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardviewt, parent,  false);

        return new Holder(tramView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.onBind(trams.get(position),callback);


    }

    @Override
    public int getItemCount() {
        return trams.size();
    }


}
