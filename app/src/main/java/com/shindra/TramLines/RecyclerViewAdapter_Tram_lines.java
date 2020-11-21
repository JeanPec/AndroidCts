package com.shindra.TramLines;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class RecyclerViewAdapter_Tram_lines extends RecyclerView.Adapter<Trams_View_Holder>
{
    private ArrayList<Line> tramLinesList;
    private Trams_View_Holder.ScheduleButtonClick callBack;

    public RecyclerViewAdapter_Tram_lines(ArrayList<Line> listelignetrams, Trams_View_Holder.ScheduleButtonClick callBack)
    {
        this.tramLinesList = listelignetrams;
        this.callBack = callBack;
    }


    RecyclerViewAdapter_Tram_lines(ArrayList<Line> List_Tram_Lines)
    {
        this.tramLinesList = List_Tram_Lines;
    }

    @NonNull
    @Override
    public Trams_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View TramView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tram_lines_view, parent, false);
        return new Trams_View_Holder(TramView);
    }

    @Override
    public void onBindViewHolder(@NonNull Trams_View_Holder holder, int position)
    {
        Line requestTramLine = tramLinesList.get(position);
        holder.onBind(requestTramLine, callBack);

    }

    @Override
    public int getItemCount()
    {
        return tramLinesList.size();
    }

}
