package com.shindra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterLigneTram extends RecyclerView.Adapter<TramsViewHolder>
{
    private ArrayList<Line> LigneTrams;
    Context context;

    //Constructeur
    RecyclerViewAdapterLigneTram(ArrayList<Line> listelignetrams)
    {
        this.LigneTrams = listelignetrams;
    }

    //Liaison avec la RecyclerView du fichier xml
    @NonNull
    @Override
    public TramsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View TramView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tableau_deroulant_ligne_tram, parent, false);
        return new TramsViewHolder(TramView);
    }

    //Liaison entre les donnees et les differente lignes sur la RecyclerView
    @Override
    public void onBindViewHolder(@NonNull TramsViewHolder holder, int position)
    {
        Line requestTramLine = LigneTrams.get(position);
        holder.onBind(requestTramLine);
    }

    //Nombre de lignes sur la RecyclerView
    @Override
    public int getItemCount()
    {
        return LigneTrams.size();
    }

}
