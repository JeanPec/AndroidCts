package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class LineAdapter extends RecyclerView.Adapter<LineViewHolder>
{
    ArrayList<Line> Data;

    public LineAdapter(ArrayList<Line> data)
    {
        this.Data = data;
    }

    @NonNull
    @Override
    public LineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_view, parent, false);
        return new LineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LineViewHolder holder, int position)
    {
        Line item = this.Data.get(position);
        switch (item.getName())
        {
            case "Ligne A":
                holder.TramIcon.setImageResource(R.drawable.tram_a);
                break;
            case "Ligne B":
                holder.TramIcon.setImageResource(R.drawable.tram_b);
                break;
            case "Ligne C":
                holder.TramIcon.setImageResource(R.drawable.tram_c);
                break;
            case "Ligne D":
                holder.TramIcon.setImageResource(R.drawable.tram_d);
                break;
            case "Ligne E":
                holder.TramIcon.setImageResource(R.drawable.tram_e);
                break;
            case "Ligne F":
                holder.TramIcon.setImageResource(R.drawable.tram_f);
                break;
            default:
                holder.TramIcon.setImageResource(R.drawable.tram);
        }
    }

    @Override
    public int getItemCount()
    {
        return this.Data.size();
    }
}
