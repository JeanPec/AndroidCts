package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class stationFragment extends Fragment {

    public RecyclerView recycler;
    public stationAdapter adapter;
    public Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_station, container,false);

        recycler=v.findViewById(R.id.recycler_view_station);
        button=v.findViewById(R.id.buttonToMapActivity);

        Intent intentFromStartActivity = getActivity().getIntent();
        String lineName = getArguments().getString("lineName");
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setHasFixedSize(true);
        //adapter = new stationAdapter(getActivity(),button,model);

        return v;
    }
}
