package com.shindra;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HoraireAdapter extends RecyclerView.Adapter<HoraireAdapter.HoraireViewHolder>{

    List<HoraireCard> listeHoraire;


    public static class HoraireViewHolder extends RecyclerView.ViewHolder {

        TextView arret, horaire, nomLigne, prochainDepart;



        @SuppressLint("WrongViewCast")
        public HoraireViewHolder(@NonNull View itemView) {
            super(itemView);
            arret = itemView.findViewById(R.id.arret);
            horaire = itemView.findViewById(R.id.horaire);
            nomLigne = itemView.findViewById(R.id.nomLigne);
            prochainDepart = itemView.findViewById(R.id.prochainDepart);

        }


    }
    public HoraireAdapter(List<HoraireCard> listeHoraire) {
        this.listeHoraire = listeHoraire;
    }

    @NonNull
    @Override
    public HoraireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_horaire, parent, false);
        HoraireAdapter.HoraireViewHolder horaireViewHolder = new HoraireAdapter.HoraireViewHolder(view);
        return horaireViewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull HoraireViewHolder holder, int position) {

        HoraireCard horaireCard = listeHoraire.get(position);

        holder.arret.setText(horaireCard.arret);
        holder.horaire.setText(horaireCard.horaire);
        holder.nomLigne.setText("Ligne "+horaireCard.nomLigne);

        switch (horaireCard.nomLigne){
            case "A":
                holder.nomLigne.setTextColor(R.color.color_Ligne_A);
                break;
            case "B":
                holder.nomLigne.setTextColor(R.color.color_Ligne_B);
                break;
            case "C":
                break;
            case "D":
                break;
            case "E":
                break;
            case "F":
                break;
        }


    }

    @Override
    public int getItemCount() {
        return listeHoraire.size();
    }







}
