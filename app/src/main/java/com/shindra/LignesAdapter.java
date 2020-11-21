//How to add click clistener to cardview :
//https://www.youtube.com/watch?v=yYVIasp5KXo

package com.shindra;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
    InterfaceRequiredLine callback; //cf slide 97

    //Data is passed into the constructor like slide 104
    LignesAdapter(List<Line> lines, InterfaceRequiredLine callback) {
        this.lines=lines;
        this.callback=callback;
    }

    //Inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    //Binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(lines.get(position), callback);

    }

    //Total number of rows
    @Override
    public int getItemCount() {
        return lines.size();
    }

    public void setLines(ArrayList<Line> lines) {
        this.lines=lines;
    }

    //Stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView myTextView;
        ImageView imageTram;
        ImageView imageLetter;
        Button scheduleButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //set values to views
            imageTram = itemView.findViewById(R.id.imageTram);
            scheduleButton = itemView.findViewById(R.id.scheduleButton);
        }

        public void onBind(Line line, InterfaceRequiredLine callback) { //2nd parameter : to do callback like in slide 97

            //Populate elements
            imageTram.setImageResource(R.drawable.ic_tram);
            String lineName = line.getName();

            scheduleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("button", "Bouton presse, ligne demandee:"+line.getName().toString());
                    callback.OnClick(line); //CRASH HERE
                }
            });

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

        interface InterfaceRequiredLine {
            void OnClick(Line line);
        }

    }

}


