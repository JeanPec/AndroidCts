package com.shindra;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;


public class HoraireFragment extends Fragment {

    public RecyclerView arrets;
    public Button seeMap;
    public String lineRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_horaire, container, false);

        arrets = rootView.findViewById(R.id.recyclerView2);
        arrets.setLayoutManager(new LinearLayoutManager(getContext()));
        arrets.setAdapter(new HoraireAdapter(new ArrayList<Stop>(), lineRef));


        seeMap = rootView.findViewById(R.id.carteButton);
        seeMap.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MapActivity.class);
            intent.putExtra("lineRef", lineRef);
            startActivity(intent);
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public HoraireFragment(String lineRef)
    {
        this.lineRef = lineRef;
    }


    public String getLineRef()
    {
        return lineRef;
    }
}