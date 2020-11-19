package com.shindra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;

public class StopAdapter extends RecyclerView.Adapter<StopViewHolder> {

    public ArrayList<Stop> stops;
    public String lineName;

  StopAdapter(ArrayList<Stop> stops, String lineName){
    this.stops = stops;
    this.lineName = lineName;
  }

    @NonNull
    @Override
    public StopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.stopview, parent, false);

      return new StopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StopViewHolder holder, int position) {
      Stop stops = this.stops.get(position);
      holder.OnBind(stops, lineName);
    }

    @Override
    public int getItemCount() {
        return stops.size();
    }

    public void setStops(ArrayList<Stop> stops) {
      this.stops = stops;
    }
}