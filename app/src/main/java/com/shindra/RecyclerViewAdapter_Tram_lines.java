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

public class RecyclerViewAdapter_Tram_lines extends RecyclerView.Adapter<Trams_View_Holder>
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
    public Trams_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View TramView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tram_lines_view, parent, false);
        return new Trams_View_Holder(TramView);
    }

    //Liaison entre les donnees et les differente lignes sur la RecyclerView
    @Override
    public void onBindViewHolder(@NonNull Trams_View_Holder holder, int position)
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
