package com.shindra;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import java.text.SimpleDateFormat;

public class MySecondAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    Line selectedLine;//take the whole line
    String letterLine;//take the letter of the line
    String stringLigne;

    public MySecondAdapter(Line line, String letter) {
            selectedLine = line;
            letterLine = letter;
            stringLigne = "Ligne "+letterLine;
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            //last parameter false for recycler view
            View view = inflater.inflate(R.layout.row_horaire, parent, false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            holder.onBind(selectedLine.getStops().get(position), stringLigne);
        }

        @Override
        public int getItemCount() {
            return selectedLine.getStops().size();
        }

    }
    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView letter,stationName,stationTime;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            letter = itemView.findViewById(R.id.LineTram);
            stationName = itemView.findViewById(R.id.StationName);
            stationTime = itemView.findViewById(R.id.StationTime);
        }

        public void onBind(Stop stop, String letterLine){

            //mettre en heure local + 1
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            String formatedDate;

            if(stop.getEstimatedDepartureTime() != null){
                formatedDate = df.format(stop.getEstimatedDepartureTime());
                //String time = stop.getEstimatedDepartureTime().toString();
                //time = time.substring(8,10) + ":" + time.substring(11,13) ;
                stationTime.setText(formatedDate);
            }

            letter.setText(letterLine);

            if(stop.getName().contains("Alt Win")){
                stationName.setText("Alt Winmärik-Vieux M.");
            }
            else if(stop.getName().contains("Ancienne Syn")){
                stationName.setText("Les Halles");
            }else stationName.setText(stop.getName());

        }

    }

