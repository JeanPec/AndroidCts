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
        holder.OnBind(newArret, lineRef);
    }

    @Override
    public int getItemCount() {
        return arrets.size();
    }

    public void setArrets(ArrayList<Stop> arrets)
    {
        this.arrets = arrets;
    }

    public void setStops(ArrayList<Stop> arrets)
    {
        this.arrets = arrets;
    }


}
