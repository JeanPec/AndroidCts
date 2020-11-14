package com.shindra.Line;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.Service.Converter;
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
        holder.tramIcon.setImageResource(Converter.lineNameToLineIcon(item.getName()));
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
}
