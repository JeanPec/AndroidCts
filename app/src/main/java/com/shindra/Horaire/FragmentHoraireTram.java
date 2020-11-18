package com.shindra.Horaire;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;


public class FragmentHoraireTram extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_horaire_tram, container, false);

        ///Creation d'un liste et d'une ligne vide
        ArrayList<Stop> ListArretTram = new ArrayList<Stop>();
        String LettreLigneTram = "A";

        RecyclerView ListStopTram = rootView.findViewById(R.id.RecyclerView_Horaire_Tram);   //Referencement vers la recyclerview "NosTrams"
        ListStopTram.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListStopTram.setAdapter(new RecyclerViewAdapterHoraireTram(ListArretTram, LettreLigneTram));

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }


    public void setParamFragment(ArrayList<Stop> listArretTram, String LettreLigne)
    {
        RecyclerView recyclerView = this.getActivity().findViewById(R.id.RecyclerView_Horaire_Tram);
        ((RecyclerViewAdapterHoraireTram) recyclerView.getAdapter()).SetListArretTram(listArretTram);
        ((RecyclerViewAdapterHoraireTram) recyclerView.getAdapter()).SetNomLigne(LettreLigne);

        //Mise a jour de l'adapter
        ((RecyclerViewAdapterHoraireTram) recyclerView.getAdapter()).notifyDataSetChanged();
    }



}