package com.shindra;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class HoraireFragment extends Fragment {

    public RecyclerView arrets;
    public Button seeMap;
    public String lineRef;
    ArrayList<Stop> stop;

    public HoraireFragment() {
    }


    public static HoraireFragment newInstance(String lineRef) {
        HoraireFragment fragment = new HoraireFragment();
        Bundle args = new Bundle();
        args.putString("lineRef" , lineRef);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_horaire, container, false);

        arrets = rootView.findViewById(R.id.recyclerViewhoraire);
        arrets.setLayoutManager(new LinearLayoutManager(getContext()));
        arrets.setAdapter(new HoraireAdapter(new ArrayList<Stop>(), lineRef));


        seeMap = rootView.findViewById(R.id.carteButton);
        seeMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HoraireFragment.this.getActivity(), MapActivity.class);
                intent.putExtra("lineRef", lineRef);
                HoraireFragment.this.startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        ActivityLoad loadPage = new ActivityLoad(getActivity());
        loadPage.showLoadingScreen();

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM , lineRef , 0) , new ObservableListener<Line>() {

            @Override
            public void onSuccess(Line data) {
                stop = new ArrayList<Stop>();
                for (Stop oneStop : data.getStops()) {
                    if (oneStop.getEstimatedDepartureTime() != null) {

                        stop.add(oneStop);
                    }
                    ((HoraireAdapter)arrets.getAdapter()).setStops(stop);
                    arrets.getAdapter().notifyDataSetChanged();
                }

                loadPage.HideLoadingScreen();
            }
            @Override
            public void onError(@NotNull Throwable throwable) {
                loadPage.HideLoadingScreen();
            }

            @Override
            public void onLoading() {

            }
        });
    }


    public HoraireFragment(String lineRef)
    {
        this.lineRef = lineRef;
    }

}