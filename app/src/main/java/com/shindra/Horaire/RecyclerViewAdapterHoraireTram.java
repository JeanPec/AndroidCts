package com.shindra.Horaire;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;

public class RecyclerViewAdapterHoraireTram extends RecyclerView.Adapter<HoraireViewHolder>
{
    private ArrayList<Stop> ListArretTram;
    private String NomLigne;

    public RecyclerViewAdapterHoraireTram(ArrayList<Stop> listArretTram, String nomLigne)
    {
        this.ListArretTram = listArretTram;
        this.NomLigne = nomLigne;
    }

    //Liaison avec la RecyclerView du fichier xml
    @NonNull
    @Override
    public HoraireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View HoraireView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_horaire_tram, parent, false);
        return new HoraireViewHolder(HoraireView);
    }

    @Override
    public void onBindViewHolder(@NonNull HoraireViewHolder holder, int position)
    {
        Stop requestStopLigne = ListArretTram.get(position);
        holder.onBind(requestStopLigne,NomLigne);
    }

    @Override
    public int getItemCount()
    {
        return ListArretTram.size();
    }

    public void SetListArretTram(ArrayList<Stop> Valeur)
    {
        ListArretTram = Valeur;
    }

    public void SetNomLigne(String Valeur)
    {
        this.NomLigne = Valeur;
    }

}
