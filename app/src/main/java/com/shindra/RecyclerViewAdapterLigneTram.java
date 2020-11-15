package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterLigneTram extends RecyclerView.Adapter<TramsViewHolder>
{
    //int ImageLigneTram[];
    private ArrayList<Integer> ListeImageLigneTrams;

    RecyclerViewAdapterLigneTram(ArrayList<Integer> listeimage)
    {
        this.ListeImageLigneTrams = listeimage;
    }

    @NonNull
    @Override
    public TramsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View TramView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tableau_deroulant_ligne_tram, parent, false);
        return new TramsViewHolder(TramView);
    }

    @Override
    public void onBindViewHolder(@NonNull TramsViewHolder holder, int position)
    {
        holder.onBind(ListeImageLigneTrams.get(position));
    }

    @Override
    public int getItemCount()
    {
        return ListeImageLigneTrams.size();
    }


}
