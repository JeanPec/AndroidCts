package com.shindra.Line;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Line;
import java.util.ArrayList;

public class LineAdapter extends RecyclerView.Adapter<LineViewHolder>
{
    ArrayList<Line> lines;
    ILineClickable callback;

    public LineAdapter(ArrayList<Line> lines, ILineClickable callback)
    {
        this.lines = lines;
        this.callback = callback;
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
        holder.OnBind(item, callback);
    }

    @Override
    public int getItemCount()
    {
        return this.lines.size();
    }

    public void setLines(ArrayList<Line> lines)
    {
        this.lines = lines;
    }

    private int getTramIcon(Line line)
    {
        switch (line.getName())
        {
            case "Parc des Sports - Illkirch Graffenstaden":
                return R.drawable.tram_a;
            case "Lingolsheim Tiergaertel - Hoenheim Gare":
                return R.drawable.tram_b;
            case "Gare Centrale - Neuhof Rodolphe Reuss":
                return R.drawable.tram_c;
            case "Poteries - Port du Rhin / Kehl Rathaus":
                return R.drawable.tram_d;
            case "Robertsau l'Escale - Campus d'Illkirch":
                return R.drawable.tram_e;
            case "Comtes - Place d'Islande":
                return R.drawable.tram_f;
            default:
                return R.drawable.tram;
        }
    }
}
