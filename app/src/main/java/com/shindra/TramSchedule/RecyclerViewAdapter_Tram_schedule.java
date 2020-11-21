package com.shindra.TramSchedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.TramLines.Trams_View_Holder;
import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class RecyclerViewAdapter_Tram_schedule extends RecyclerView.Adapter<Trams_View_Holder>
{
    private ArrayList<Line> tram_lines;
    //Context context;

    RecyclerViewAdapter_Tram_schedule(ArrayList<Line> List_Tram_Lines)
    {
        this.tram_lines= List_Tram_Lines;
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
        Line requestTramLine = tram_lines.get(position);
        holder.onBind_logo(requestTramLine);

    }

    @Override
    public int getItemCount()
    {
        return tram_lines.size();
    }

}
