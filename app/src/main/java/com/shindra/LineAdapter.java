package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shindra.ctslibrary.bo.Line;
import java.util.ArrayList;

public class LineAdapter extends RecyclerView.Adapter<LineViewHolder>
{
    ArrayList<Line> lines;

    public LineAdapter(ArrayList<Line> lines)
    {
        this.lines = lines;
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
        Line item = lines.get(position);
        holder.tramIcon.setImageResource(getTramIcon(item));
    }

    @Override
    public int getItemCount()
    {
        return this.lines.size();
    }

    private int getTramIcon(Line line)
    {
        switch (line.getName())
        {
            case "Ligne A":
                return R.drawable.tram_a;
            case "Ligne B":
                return R.drawable.tram_b;
            case "Ligne C":
                return R.drawable.tram_c;
            case "Ligne D":
                return R.drawable.tram_d;
            case "Ligne E":
                return R.drawable.tram_e;
            case "Ligne F":
                return R.drawable.tram_f;
            default:
                return R.drawable.tram;
        }
    }
}
