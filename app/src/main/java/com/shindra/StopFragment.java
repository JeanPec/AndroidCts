package com.shindra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;


public class StopFragment extends Fragment {

    public RecyclerView stops;
    public String lineName;

    public StopFragment (String lineName){
        this.lineName = lineName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.stopfragment, container, false);

        stops = view.findViewById(R.id.recyclerView_stop);
        stops.setLayoutManager(new LinearLayoutManager(getContext()));
        stops.setAdapter(new StopAdapter(new ArrayList<Stop>(), lineName));
        return view;


    }

    public void updateWidget(ArrayList<Stop> data){
        ((StopAdapter) stops.getAdapter()).setStops(data);
        stops.getAdapter().notifyDataSetChanged();
    }

    public String getLineName(){
        return lineName;
    }
}