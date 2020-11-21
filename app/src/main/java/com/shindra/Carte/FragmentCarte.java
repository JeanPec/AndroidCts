package com.shindra.Carte;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.shindra.MyViewModel;
import com.shindra.R;
import com.shindra.Utilites.DialogDeChargement;
import com.shindra.arrakis.controls.MapFragment;
import com.shindra.arrakis.controls.Poi;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCarte#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCarte extends MapFragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;
    private String LettreLigne;
    private ArrayList<Poi> ListeCoorArretTram;  //Liste contenant les Arrets d'une ligne de tram

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param Ligne Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */

    public static FragmentCarte newInstance(String Ligne) {
        FragmentCarte fragment = new FragmentCarte();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, Ligne);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    //Liaison avec le fragment de la carte
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LettreLigne = this.getArguments().getString(ARG_PARAM1);
        View CarteView = inflater.inflate(R.layout.fragment_gmaps, container, false);

        return CarteView;
    }

    public void onStart()
    {
        super.onStart();

        //Alert dialog, Creation de la ProgressBar de chargement
        DialogDeChargement dialogDeChargement = new DialogDeChargement(getActivity());

        //Realisation de l'appel réseau
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithStop(RouteType.TRAM, LettreLigne), new ObservableListener<Line>()
        {
            @Override
            public void onLoading()
            {
                //call once we started the network called. Indicate that the network call is in progress
                Log.i(getString(R.string.Txt_page_Carte),"Chargement de la page Carte");

                //Affiche la ProgressBar
                dialogDeChargement.AfficherPageChargement();
            }

            @Override
            public void onSuccess(Line data)
            {
                //call once the network call has responded with a success
                Log.i(getString(R.string.Txt_page_Carte),"Success reception des données");

                //Remplissage dynamique des tableaux des lignes de trams
                ListeCoorArretTram = new ArrayList<Poi>();
                for (Stop listStop : data.getStops())
                {
                    ListeCoorArretTram.add(new Poi(R.drawable.icon_maps_place_24px, GetLineColor(LettreLigne), listStop.getPosition().getLatitude(), listStop.getPosition().getLongitude()));
                }

                addPois(ListeCoorArretTram);

                //Enleve l'affichage de la ProgressBar
                dialogDeChargement.EnleverPageChargement();
            }

            @Override
            public void onError(@NotNull Throwable throwable)
            {
                //call if the network call has responded with an error
                Log.e(getString(R.string.Txt_page_Carte),"Erreur Application");
            }
        });
    }

    //Permet d'obtenir la couleur en fonction de la ligne
    private int GetLineColor(String LineLetter)
    {
        int color;

        switch (LineLetter)
        {
            case "A":
                color = R.color.TramLineA;
                break;

            case "B":
                color = R.color.TramLineB;
                break;

            case "C":
                color = R.color.TramLineC;
                break;

            case "D":
                color = R.color.TramLineD;
                break;

            case "E":
                color = R.color.TramLineE;
                break;

            case "F":
                color = R.color.TramLineF;
                break;

            default:
                color = R.color.black;
                break;
        }
        return color;
    }
}