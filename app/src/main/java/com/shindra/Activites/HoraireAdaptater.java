package com.shindra.Activites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;

public class HoraireAdaptater extends RecyclerView.Adapter<HoraireViewHolder> {


    @NonNull
    @Override
    public HoraireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View horaireView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.view_line_horaire_layout,parent, false);

        return new HoraireViewHolder(horaireView);
    }

    @Override
    public void onBinViewHolder(@NonNull HoraireViewHolder, int position){
        holder.onBind()
    }

    @Override
    public int getItemCount() {

    }
}
