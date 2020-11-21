package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {

    ArrayList<Line> lines;
    RecyclerItemClick callback;

    RVAdapter(ArrayList<Line> lines,RecyclerItemClick callback){
        this.lines = lines;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Line newLigne = lines.get(position);
        //holder.image_tram_x.setImageResource(Image(newLigne));
        holder.OnBind(newLigne,callback,position);
    }

    @Override
    public int getItemCount() {
        return lines.size();
    }

    public void setLine(ArrayList<Line> lines)
    {
        this.lines = lines;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image_tram_x, image_photo;
        Button Bhoraire;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_tram_x = itemView.findViewById(R.id.imageView);
            image_photo = itemView.findViewById(R.id.imageView2);
            this.Bhoraire = itemView.findViewById((R.id.button));
        }
        public void OnBind(Line line, RecyclerItemClick callback, int position)
        {
            Bhoraire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    callback.OnClick(line);
                }
            });
            Line newLigne = lines.get(position);
            image_tram_x.setImageResource(Image(newLigne));
        }

    }

    private int Image(Line line)
    {
        switch (line.getName())
        {
            case "A":
                return R.drawable.tram_a;
            case "B":
                return R.drawable.tram_b;
            case "C":
                return R.drawable.tram_c;
            case "D":
                return R.drawable.tram_d;
            case "E":
                return R.drawable.tram_e;
            case "F":
                return R.drawable.tram_f;
            default:
                return R.drawable.tram;
        }
    }

}
