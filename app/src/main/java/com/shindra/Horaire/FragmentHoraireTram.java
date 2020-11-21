package com.shindra.Horaire;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.Carte.ActivityCarte;
import com.shindra.MyViewModel;
import com.shindra.R;
import com.shindra.Utilites.DialogDeChargement;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHoraireTram#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHoraireTram extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;
    private String LettreLigne;
    private ArrayList<Stop> ListeArretTram;  //Liste contenant les Arrets d'une ligne de tram
    RecyclerView ListeHoraireTramRV;
    private Button BtnCarteLigneTram;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param Ligne Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    //Creation de l'instance du fragment
    public static FragmentHoraireTram newInstance(String Ligne) {
        FragmentHoraireTram fragment = new FragmentHoraireTram();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LettreLigne = this.getArguments().getString(ARG_PARAM1);
        View HoraireView = inflater.inflate(R.layout.fragment_horaire_tram, container, false);
        ListeHoraireTramRV = HoraireView.findViewById(R.id.RecyclerView_Horaire_Tram);   //Referencement vers la recyclerview "Horaires"
        ListeHoraireTramRV.setLayoutManager(new LinearLayoutManager(getContext()));

        // Callback de detection du bouton appuyé
        BtnCarteLigneTram = HoraireView.findViewById(R.id.button_Map);
        BtnCarteLigneTram.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i(getString(R.string.Txt_page_Horaires),"Appuie BTN Map Ligne " + LettreLigne);
                //Creation de la nouvelle Intent
                Intent intent = new Intent(getActivity(), ActivityCarte.class);
                intent.putExtra("LettreLigneTram",LettreLigne);
                startActivity(intent);
            }
        });

        return HoraireView;
    }

    public void onStart()
    {
        super.onStart();

        //Alert dialog, Creation de la ProgressBar de chargement
        DialogDeChargement dialogDeChargement = new DialogDeChargement(getActivity());

        //Realisation de l'appel réseau
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, LettreLigne, 0), new ObservableListener<Line>()
        {
            @Override
            public void onLoading()
            {
                //call once we started the network called. Indicate that the network call is in progress
                Log.i(getString(R.string.Txt_page_Horaires),"Chargement de la page Horaire");

                //Affiche la ProgressBar
                dialogDeChargement.AfficherPageChargement();
            }

            @Override
            public void onSuccess(Line data)
            {
                //call once the network call has responded with a success
                Log.i(getString(R.string.Txt_page_Horaires),"Success reception des données");

                //Remplissage dynamique des tableaux des lignes de trams
                ListeArretTram = new ArrayList<Stop>();
                for (Stop listStop : data.getStops())
                {
                    if (listStop.getEstimatedDepartureTime() != null)
                    {
                        ListeArretTram.add(listStop);
                    }
                    ListeHoraireTramRV.setAdapter(new RecyclerViewAdapterHoraireTram(ListeArretTram, LettreLigne));
                }

                //Enleve l'affichage de la ProgressBar
                dialogDeChargement.EnleverPageChargement();
            }

            @Override
            public void onError(@NotNull Throwable throwable)
            {
                //call if the network call has responded with an error
                Log.e(getString(R.string.Txt_page_Horaires),"Erreur Application");
            }
        });
    }
}