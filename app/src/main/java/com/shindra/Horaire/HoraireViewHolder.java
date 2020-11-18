package com.shindra.Horaire;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;

public class HoraireViewHolder extends RecyclerView.ViewHolder
{
    //Widjets de la vue "Nos Trams"
    public final TextView arret;
    public final TextView horaire;
    public final TextView depart;
    public final TextView ligne;


    public HoraireViewHolder(@NonNull View itemView)
    {
        super(itemView);
        arret = itemView.findViewById(R.id.arret);
        horaire = itemView.findViewById(R.id.horaire);
        depart = itemView.findViewById(R.id.depart);
        ligne = itemView.findViewById(R.id.ligne);
    }
}
