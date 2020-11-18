package com.shindra.Activites;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;

public class HoraireViewHolder extends RecyclerView.ViewHolder {

    private final TextView stop;
    private final TextView ligneTram;
    private final TextView nextHoraire;
    private final TextView nextStart;

    public HoraireViewHolder(@NonNull View itemView){
        super(itemView);

        stop = itemView.findViewById(R.id.stoptram);
        ligneTram = itemView.findViewById(R.id.ligne_tram);
        nextHoraire = itemView.findViewById(R.id.horaire);
        nextStart= itemView.findViewById(R.id.ProchainDepart);

    public void onBind()

    }

}
