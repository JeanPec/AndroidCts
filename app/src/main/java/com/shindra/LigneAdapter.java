package com.shindra;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class LigneAdapter extends RecyclerView.Adapter<LigneViewHolder> {
    public ArrayList<Line> line;
    RecyclerItemClick callback;

    LigneAdapter(ArrayList<Line> line, RecyclerItemClick callback){
        this.line = line;
        this.callback = callback;
    }

    @NonNull
    @Override
    public LigneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ligne,parent,false);
        return new LigneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LigneViewHolder holder, int position) {
        Line newLigne = line.get(position);
        holder.image.setImageResource(getImage(newLigne));
        holder.OnBind(newLigne, callback);
    }

    @Override
    public int getItemCount() {
        return line.size();
    }

    public void setLine(ArrayList<Line> line)
    {
        this.line = line;
    }

    private int getImage(Line line)
    {
        switch (line.getName())
        {
            case "A":
                return R.drawable.ic_tram_a;
            case "B":
                return R.drawable.ic_tram_b;
            case "C":
                return R.drawable.ic_tram_c;
            case "D":
                return R.drawable.ic_tram_d;
            case "E":
                return R.drawable.ic_tram_e;
            case "F":
                return R.drawable.ic_tram_f;
            default:
                return R.drawable.ic_tram;
        }
    }
}
