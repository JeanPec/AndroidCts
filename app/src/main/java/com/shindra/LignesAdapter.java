package com.shindra;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.apibo.LineApi;
import com.shindra.ctslibrary.apibo.Lines;
import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;
import java.util.List;

public class LignesAdapter extends RecyclerView.Adapter<LignesAdapter.ViewHolder> {

    private Context context;
    private List<Line> lines;
    private LayoutInflater mInflater;

    //Data is passed into the constructor like slide 104
    LignesAdapter(List<Line> lines) {
        this.lines=lines;

    }

    //Inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lineView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(lineView);
    }

    //Binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(lines.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Bouton appuye", Toast.LENGTH_SHORT).show();
                Log.d("button", "Bouton presse");
            }

        });

    }

    //Total number of rows
    @Override
    public int getItemCount() {
        return lines.size();
    }

    //Stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        ImageView imageTram;
        ImageView imageLetter;

        ViewHolder(View itemView) {
            super(itemView);
            //set values to views
            imageTram = itemView.findViewById(R.id.imageTram);
        }

        public void onBind(Line line) {
            //Populate elements
            imageTram.setImageResource(R.drawable.ic_tram);
            String lineName = line.getName();
            int listSize = lines.size();
            for (int i=0 ; i<listSize ; i++) {
                imageLetter = itemView.findViewById(R.id.imageLetter);
                switch(lineName) {
                    case "A":
                        imageLetter.setImageResource(R.drawable.ic_a);
                        break;
                    case "B" :
                        imageLetter.setImageResource(R.drawable.ic_b);
                        break;
                    case "C" :
                        imageLetter.setImageResource(R.drawable.ic_c);
                        break;
                    case "D" :
                        imageLetter.setImageResource(R.drawable.ic_d);
                        break;
                    case "E" :
                        imageLetter.setImageResource(R.drawable.ic_e);
                        break;
                    case "F" :
                        imageLetter.setImageResource(R.drawable.ic_f);
                        break;
                }
            }


        }

    }

}


