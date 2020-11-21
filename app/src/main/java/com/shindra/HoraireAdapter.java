package com.shindra;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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

        public void onBind(HoraireCard horaireCard)
        {

            String ligne = horaireCard.nomLigne;
            //nomLigne.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.color_Ligne_B));

            arret.setText(horaireCard.arret);
            horaire.setText(horaireCard.horaire);


            switch(ligne){
                case "A":
                    nomLigne.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.color_Ligne_A));
                    break;
                case "B":
                    nomLigne.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.color_Ligne_B));
                    break;
                case "C":
                    nomLigne.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.color_Ligne_C));
                    break;
                case "D":
                    nomLigne.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.color_Ligne_D));
                    break;
                case "E":
                    nomLigne.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.color_Ligne_E));
                    break;
                case "F":
                    nomLigne.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.color_Ligne_F));
                    break;
            }
            nomLigne.setText("Ligne "+ligne);

           // arret.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.color_Ligne_B));

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


    @Override
    public void onBindViewHolder(@NonNull HoraireViewHolder holder, int position) {

        HoraireCard horaireCard = listeHoraire.get(position);


        holder.onBind(horaireCard);




    }

    @Override
    public int getItemCount() {
        return listeHoraire.size();
    }







}
