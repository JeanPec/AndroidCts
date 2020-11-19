package com.shindra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
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

import org.jetbrains.annotations.NotNull;

public class HoraireFragment extends Fragment {

    private RecyclerView recyclerView;
    Line selectedLine;
    String letterLine;

    public HoraireFragment(String letter ) {
        super();
        letterLine = letter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Network call for the selected line
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, letterLine, 0),
                new ObservableListener<Line>() {

                    @Override
                    public void onError(@NotNull Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(Line data) {
                         selectedLine = data;

                        recyclerView.setAdapter(new MySecondAdapter(selectedLine, letterLine));

                       /*
                        SelectedLineStopName = data.getStops().get(0).getName();
                        SelectedLineNextDeparture = data.getStops().get(0).getEstimatedDepartureTime().toString();
                        Log.d(TAGG,"my data : " + SelectedLineStopName);
                        Log.d(TAGG,"my data 2 : " + SelectedLineNextDeparture);
                        Log.d(TAGG,"my data 2 : " + data);
                        */
                    }

                    @Override
                    public void onLoading() {

                    }
                });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_horaire, container, false);

        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview_horaire);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }
}