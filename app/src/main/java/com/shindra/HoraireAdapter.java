package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Stop;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoraireAdapter extends RecyclerView.Adapter<HoraireViewHolder> {
    ArrayList<Stop> arrets;
    String lineName;

    public HoraireAdapter(ArrayList<Stop> arrets, String lineName){
        this.arrets = arrets;
        this.lineName = lineName;
    }

    @NonNull
    @Override
    public HoraireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_horaire, parent, false);
        return new HoraireViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoraireViewHolder holder, int position) {
        Stop newArret = arrets.get(position);
        holder.Headline1.setText(newArret.getName());
        holder.Ligne.setText(getInfos(lineName));
        holder.Ligne.setTextColor(ContextCompat.getColor(holder.Ligne.getContext(), getColor(lineName)));
        SimpleDateFormat fmt = new SimpleDateFormat("HH'h'mm");
        holder.Time.setText(fmt.format(newArret.getEstimatedDepartureTime()));

    }

    @Override
    public int getItemCount() {
        return arrets.size();
    }

    public void setArrets(ArrayList<Stop> arrets)
    {
        this.arrets = arrets;
    }


    private String getInfos(String lineName)
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

    private int getColor(String lineName)
    {
        switch (lineName)
        {
            case "Parc des Sports - Illkirch Graffenstaden":
                return R.color.ligne_a;
            case "Lingolsheim Tiergaertel - Hoenheim Gare":
                return R.color.ligne_b;
            case "Gare Centrale - Neuhof Rodolphe Reuss":
                return R.color.ligne_c;
            case "Poteries - Port du Rhin / Kehl Rathaus":
                return R.color.ligne_d;
            case "Robertsau l'Escale - Campus d'Illkirch":
                return R.color.ligne_e;
            case "Comtes - Place d'Islande":
                return R.color.ligne_f;
            default:
                return R.color.black;
        }
    }

}
