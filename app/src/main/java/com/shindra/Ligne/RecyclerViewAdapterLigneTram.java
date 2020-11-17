package com.shindra.Ligne;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class RecyclerViewAdapterLigneTram extends RecyclerView.Adapter<TramsViewHolder>
{
    private ArrayList<Line> LigneTrams;
    private TramsViewHolder.RecyclerHoraireClick callBack;

    //Constructeur
    public RecyclerViewAdapterLigneTram(ArrayList<Line> listelignetrams, TramsViewHolder.RecyclerHoraireClick callBack)
    {
        this.LigneTrams = listelignetrams;
        this.callBack = callBack;
    }

    //Liaison avec la RecyclerView du fichier xml
    @NonNull
    @Override
    public TramsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View TramView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ligne_tram, parent, false);
        return new TramsViewHolder(TramView);
    }

    //Liaison entre les donnees et les differente lignes sur la RecyclerView
    @Override
    public void onBindViewHolder(@NonNull TramsViewHolder holder, int position)
    {
        Line requestLigneTram = LigneTrams.get(position);
        holder.onBind(requestLigneTram, callBack);
    }

    //Nombre de lignes sur la RecyclerView
    @Override
    public int getItemCount()
    {
        return LigneTrams.size();
    }

}
