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
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.QuickContactBadge;


/*public class FragmentA extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public View rootView;

    public static FragmentA newInstance(){
        return new FragmentA();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        rootView = inflater.inflate(R.layout.card, container,false);

        recyclerView =  rootView.findViewById(R.id.listeTramRV);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RVAdapter();
        recyclerView.setAdapter(adapter);



        return super.onCreateView(inflater,container,savedInstanceState);
    }



    /*private View.OnClickListener BHoraireListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }*/
    //}
