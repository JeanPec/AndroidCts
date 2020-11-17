package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shindra.ctslibrary.bo.Line;

import java.util.*;

public class RecyclerViewAdapter extends RecyclerView.Adapter<TramViewHolder> {

    public ArrayList<Line> listTrams;
    TramViewHolder.onButtonClickListener callback;

    RecyclerViewAdapter(ArrayList<Line> listTrams, TramViewHolder.onButtonClickListener callback){
        this.listTrams = listTrams;
        this.callback = callback;
    }

    @NonNull
    @Override
    public TramViewHolder onCreateViewHolder(@NonNull ViewGroup vgParent, int iViewType) {
        View vTrams = LayoutInflater.from(vgParent.getContext()).inflate(R.layout.tramlayout,vgParent,false);

        return new TramViewHolder(vTrams);
    }

    @Override
    public void onBindViewHolder(@NonNull TramViewHolder holder, int iPosition) {
        holder.onBind(listTrams.get(iPosition),callback);
    }

    @Override
    public int getItemCount() {
        return listTrams.size();
    }
}
