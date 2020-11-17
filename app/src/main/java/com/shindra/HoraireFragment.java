package com.shindra;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;


public class HoraireFragment extends Fragment {

    String lineName;
    RecyclerView arrets;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_horaire, container, false);
        arrets = rootView.findViewById(R.id.recyclerView2);
        lineName = this.getArguments().getString("lineName");
        arrets.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrets.setAdapter(new HoraireAdapter(new ArrayList<Stop>(), lineName));
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}