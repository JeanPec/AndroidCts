package com.shindra.Horaire;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;

public class HoraireViewHolder extends RecyclerView.ViewHolder
{
    //Widjets de la vue "Nos Trams"
    private final TextView Arret;
    private final TextView Horaire;
    private final TextView Depart;
    private final TextView Ligne;


    public HoraireViewHolder(@NonNull View itemView)
    {
        super(itemView);
        Arret = itemView.findViewById(R.id.Arret);
        Horaire = itemView.findViewById(R.id.Horaire);
        Depart = itemView.findViewById(R.id.Depart);
        Ligne = itemView.findViewById(R.id.Ligne);
    }
}
