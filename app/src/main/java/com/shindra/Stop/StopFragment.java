package com.shindra.Stop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.Map.MapActivity;
import com.shindra.R;
import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;

public class StopFragment extends Fragment
{
    private RecyclerView stops;
    private Button seeOnMapButton;

    private String lineName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.stop_fragment, container, false);

        stops = view.findViewById(R.id.stops);
        stops.setLayoutManager(new LinearLayoutManager(getContext()));
        stops.setAdapter(new StopAdapter(new ArrayList<Stop>(), lineName));

        seeOnMapButton = view.findViewById(R.id.seeOnMapButton);
        seeOnMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                intent.putExtra("lineName",lineName);
                startActivity(intent);
            }
        });

        return view;
    }

    public StopFragment(String lineName)
    {
        this.lineName = lineName;
    }

    public void updateWidgets(ArrayList<Stop> data)
    {
        ((StopAdapter) stops.getAdapter()).setStops(data);
        stops.getAdapter().notifyDataSetChanged();
    }

    public String getLineName()
    {
        return lineName;
    }
}
