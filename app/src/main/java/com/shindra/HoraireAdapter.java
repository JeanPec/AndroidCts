package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class HoraireAdapter extends RecyclerView.Adapter<HoraireAdapter.MyViewHolderHoraire> {
    ArrayList<Stop> arrets;
    String nomLigne;

    public HoraireAdapter(ArrayList<Stop> arrets, String nomLigne) {
        this.arrets = arrets;
        this.nomLigne = nomLigne;
    }

    @NonNull
    @Override
    public MyViewHolderHoraire onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardhoraire,parent,false);
        return new MyViewHolderHoraire(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderHoraire holder, int position) {
        Stop NouvArret = arrets.get(position);
        holder.HeadLine1.setText(NouvArret.getName());
        holder.Ligne.setText(getInfo(nomLigne));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH'h'mm");
        holder.Temps.setText(simpleDateFormat.format(NouvArret.getEstimatedDepartureTime()));
    }

    @Override
    public int getItemCount() {
        return arrets.size();
    }

    public void setArrets(ArrayList<Stop> arrets)
    {
        this.arrets = arrets;
    }


    public class MyViewHolderHoraire extends RecyclerView.ViewHolder{
        public TextView HeadLine1;
        public TextView Ligne;
        public TextView Temps;

        public MyViewHolderHoraire(@NonNull View itemView){
            super(itemView);
            HeadLine1 = itemView.findViewById(R.id.Headline1);
            Ligne = itemView.findViewById(R.id.Ligne);
            Temps = itemView.findViewById(R.id.Temps);
        }


    }

    private String getInfo(String lineName)
    {
        switch (lineName)
        {
            case "Parc des Sports - Illkirch Graffenstaden":
                return "Ligne A";
            case "Lingolsheim Tiergaertel - Hoenheim Gare":
                return "Ligne B";
            case "Gare Centrale - Neuhof Rodolphe Reuss":
                return "Ligne C";
            case "Poteries - Port du Rhin / Kehl Rathaus":
                return "Ligne D";
            case "Robertsau l'Escale - Campus d'Illkirch":
                return "Ligne E";
            case "Comtes - Place d'Islande":
                return "Ligne F";
            default:
                return " ";
        }
    }



}
