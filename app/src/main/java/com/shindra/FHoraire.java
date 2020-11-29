package com.shindra;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;
import com.shindra.dummy.Tram;
import com.shindra.dummy.tramAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class FHoraire extends Fragment {

    RecyclerView recycler;
    String ligne;
    ArrayList<Stop> horaires = new ArrayList<>();

    public FHoraire() {
        super(R.layout.fragment_f_horaire);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstacesState){
        recycler = view.findViewById(R.id.RecylcerView);

        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recycler.setAdapter(new stopAdapter(horaires, new stopHolder.onButtonClickListener() {
            @Override
            public void onButtonClick(Stop stop) {

            }
        },
                ligne));
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String ligne = requireArguments().getString("ligne");

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, ligne, 1), new ObservableListener<Line>() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onSuccess(Line data) {
                horaires = data.getStops();
                ((stopAdapter) recycler.getAdapter()).setLigne(ligne);
                ((stopAdapter) recycler.getAdapter()).setStops(horaires);
                recycler.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onError(@NotNull Throwable throwable) {

            }
        });

    }


}