package com.shindra.Horaire;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Stop;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        Stop requestStopTram = ListArretTram.get(position);
        holder.horaire.setText(DateToString(requestStopTram.getEstimatedDepartureTime()));
        holder.arret.setText(requestStopTram.getName());
        holder.ligne.setTextColor(ContextCompat.getColor(holder.ligne.getContext(), GetLineColor(NomLigne)));
        holder.ligne.setText("Ligne " +NomLigne);

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


    //Permet la converssion d'un format date en un format string
    private String DateToString(Date date)
    {
        SimpleDateFormat Conv = new SimpleDateFormat("HH'h'mm");
        return Conv.format(date);
    }

    //Permet d'obtenir la couleur en fonction de la ligne
    private int GetLineColor(String LineLetter)
    {
        int color;

        switch (LineLetter)
        {
            case "A":
                color = R.color.TramLineA;
            break;

            case "B":
                color = R.color.TramLineB;
            break;

            case "C":
                color = R.color.TramLineC;
            break;

            case "D":
                color = R.color.TramLineD;
            break;

            case "E":
                color = R.color.TramLineE;
            break;

            case "F":
                color = R.color.TramLineF;
            break;

            default:
                color = R.color.black;
            break;
        }
        return color;
    }
}
