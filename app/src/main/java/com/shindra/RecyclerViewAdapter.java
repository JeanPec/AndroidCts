package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<TramViewHolder> {

    private ArrayList<Integer> listTrams;

    RecyclerViewAdapter(ArrayList<Integer> listTrams){
        this.listTrams = listTrams;
    }


    @NonNull
    @Override
    public TramViewHolder onCreateViewHolder(@NonNull ViewGroup vgParent, int iViewType) {
        View vTrams = LayoutInflater.from(vgParent.getContext()).inflate(R.layout.tramlayout,vgParent,false);

        return new TramViewHolder(vTrams);
    }

    @Override
    public void onBindViewHolder(@NonNull TramViewHolder holder, int iPosition) {
        holder.onBind(listTrams.get(iPosition));
    }

    @Override
    public int getItemCount() {
        return listTrams.size();
    }
}
