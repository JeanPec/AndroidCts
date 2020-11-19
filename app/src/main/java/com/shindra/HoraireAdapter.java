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
    String lineRef;

    public HoraireAdapter(ArrayList<Stop> arrets, String lineRef){
        this.arrets = arrets;
        this.lineRef = lineRef;
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
        holder.Ligne.setText(getInfos(lineRef));
        holder.Ligne.setTextColor(ContextCompat.getColor(holder.Ligne.getContext(), getColor(lineRef)));
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


    private String getInfos(String lineRef)
    {
        switch (lineRef)
        {
            case "A":
                return "Ligne A";
            case "B":
                return "Ligne B";
            case "C":
                return "Ligne C";
            case "D":
                return "Ligne D";
            case "E":
                return "Ligne E";
            case "F":
                return "Ligne F";
            default:
                return " ";
        }
    }

    private int getColor(String lineRef)
    {
        switch (lineRef)
        {
            case "A":
                return R.color.ligne_a;
            case "B":
                return R.color.ligne_b;
            case "C":
                return R.color.ligne_c;
            case "D":
                return R.color.ligne_d;
            case "E":
                return R.color.ligne_e;
            case "F":
                return R.color.ligne_f;
            default:
                return R.color.black;
        }
    }

}
