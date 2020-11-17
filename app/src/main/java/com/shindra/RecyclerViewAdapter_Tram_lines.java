package com.shindra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter_Tram_lines extends RecyclerView.Adapter<TramsViewHolder>
{
    private ArrayList<Line> tram_lines;
    Context context;

    //Constructeur
    RecyclerViewAdapter_Tram_lines(ArrayList<Line> listelignetrams)
    {
        this.tram_lines= listelignetrams;
    }

    //Liaison avec la RecyclerView du fichier xml
    @NonNull
    @Override
    public TramsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View TramView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tram_lines_view, parent, false);
        return new TramsViewHolder(TramView);
    }

    //Liaison entre les donnees et les differente lignes sur la RecyclerView
    @Override
    public void onBindViewHolder(@NonNull TramsViewHolder holder, int position)
    {
        Line requestTramLine = tram_lines.get(position);
        holder.onBind(requestTramLine);
    }

    //Nombre de lignes sur la RecyclerView
    @Override
    public int getItemCount()
    {
        return tram_lines.size();
    }

}
