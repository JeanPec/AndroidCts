package com.shindra.Horaire;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.Carte.ActivityCarte;
import com.shindra.MyViewModel;
import com.shindra.R;
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


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String LettreLigne;
    private ArrayList<Stop> ListeArretTram;  //Liste contenant les Arrets d'une ligne de tram
    private AlertDialog CircularProgressBar;
    private String NomPage = "Horaire";  //this.getString(R.string.page_Horaires);
    RecyclerView ListeHoraireTramRV;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param Ligne Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
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
            mParam2 = getArguments().getString(ARG_PARAM1);
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
        return HoraireView;
    }




    public void onStart()
    {
        super.onStart();

        //Alert dialog, chargement de vue
        AlertDialog.Builder CreateProgressBar = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        CreateProgressBar.setView(inflater.inflate(R.layout.loading_view, null));
        CircularProgressBar = CreateProgressBar.create();

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, LettreLigne, 0), new ObservableListener<Line>()
        {
            @Override
            public void onLoading()
            {
                //call once we started the network called. Indicate that the network call is in progress
                Log.i(NomPage,"Chargement de la page Horaire");

                //Affiche la ProgressBar
                CircularProgressBar.show();
            }

            @Override
            public void onSuccess(Line data)
            {
                //call once the network call has responded with a success
                Log.i(LettreLigne,"Success reception des données");

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
                CircularProgressBar.dismiss();

            }

            @Override
            public void onError(@NotNull Throwable throwable)
            {
                //call if the network call has responded with an error
                Log.e(NomPage,"Erreur Application, Vue: Horaire");
            }
        });
    }

    public void onMapClick(String Ligne)
    {
        Log.i(NomPage,"Appuie BTN Map Ligne " + Ligne);
        //Creation de la nouvelle Intent
        Intent intent = new Intent(getActivity(), ActivityCarte.class);
        intent.putExtra("LettreLigneTram",Ligne);
        startActivity(intent);
    }
}