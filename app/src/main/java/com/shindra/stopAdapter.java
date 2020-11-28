package com.shindra;

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
    stopHolder.onButtonClickListener callback;


    public stopAdapter(ArrayList<Stop> trams, stopHolder.onButtonClickListener callback) {
        this.stops = trams;
        this.callback = callback;
    }

    public void setStops(ArrayList<Stop> stops) {
        this.stops = stops;
    }

    @NonNull
    @Override
    public stopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View stopView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardviewh, parent,  false);

        return new stopHolder(stopView);
    }

    @Override
    public void onBindViewHolder(@NonNull stopHolder holder, int position) {
        holder.onBind(stops.get(position),callback);


    }

    @Override
    public int getItemCount() {
        return stops.size();
    }


}

class stopHolder extends RecyclerView.ViewHolder{

    private final TextView Headline;
    private final TextView heure;
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    public stopHolder(@NonNull View itemView) {
        super(itemView);
        Headline = itemView.findViewById(R.id.Headline);
        heure = itemView.findViewById(R.id.heure);

    }

    public void onBind(@NotNull Stop stop, stopHolder.onButtonClickListener callback){
        Headline.setText(stop.getName());
        heure.setText(format.format(stop.getEstimatedDepartureTime()));

    }

    public interface onButtonClickListener {
        void onButtonClick(Stop stop);
    }
}
