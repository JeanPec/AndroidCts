package com.shindra;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Stop;
import com.shindra.dummy.Tram;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class stopAdapter extends RecyclerView.Adapter<stopHolder>{
    private ArrayList<Stop> stops;
    private String ligne;


    public stopAdapter(ArrayList<Stop> stops,  String ligne) {
        this.stops = stops;
        this.ligne=ligne;

    }

    public void setStops(ArrayList<Stop> stops) {
        this.stops = stops;
    }
    public void setLigne(String line){this.ligne = line;}

    @NonNull
    @Override
    public stopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View stopView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardviewh, parent,  false);

        return new stopHolder(stopView);
    }

    @Override
    public void onBindViewHolder(@NonNull stopHolder holder, int position) {
        holder.onBind(stops.get(position),ligne);


    }

    @Override
    public int getItemCount() {
        return stops.size();
    }


}

class stopHolder extends RecyclerView.ViewHolder{

    private final TextView Headline;
    private final TextView heure;
    private final TextView Bodyline;
    private final TextView Body;
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    public stopHolder(@NonNull View itemView) {
        super(itemView);
        Headline = itemView.findViewById(R.id.Headline);
        heure = itemView.findViewById(R.id.heure);
        Bodyline = itemView.findViewById(R.id.Bodyline);
        Body = itemView.findViewById(R.id.Body);

    }

    public void onBind(@NotNull Stop stop, String line){
        Headline.setText(stop.getName());
        heure.setText(format.format(stop.getEstimatedArrivalTime()));
        Bodyline.setText("Ligne "+line);
        switch (line){
            case "A":
                Bodyline.setTextColor(Color.parseColor("#D8232A"));
                break;
            case "B":
                Bodyline.setTextColor(Color.parseColor("#00A3D4"));
                break;
            case "C":
                Bodyline.setTextColor(Color.parseColor("#EB8B2D"));
                break;
            case "D":
                Bodyline.setTextColor(Color.parseColor("#009F4A"));
                break;
            case "E":
                Bodyline.setTextColor(Color.parseColor("#7F78AB"));
                break;
            case "F":
                Bodyline.setTextColor(Color.parseColor("#84BF41"));
                break;
            default:
                Bodyline.setTextColor(Color.parseColor("#000000"));
                break;
        }
        Body.setText("Prochain départ");

    }

    public interface onButtonClickListener {
        void onButtonClick(Stop stop);
    }
}
