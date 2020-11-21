package com.shindra;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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

public class HoraireFragment extends Fragment {

    private static final String TA = "Frrag";
    private FragmentActivity myContext;
    private RecyclerView secondRecyclerView;
    Line selectedLine;
    String letterLine;

    public HoraireFragment(String letter) {
        super();
        letterLine = letter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for the loading dialog during onError or onLoading
        DialogFragment DialogCTS2 = new DialogProgress();

        //network call for the selected line
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        //choose the estimated time table for the selected line
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, letterLine, 0),
                new ObservableListener<Line>() {

                    @Override
                    public void onError(@NotNull Throwable throwable) {
                        DialogCTS2.show(getChildFragmentManager(), "onErrorDialog");
                    }

                    @Override
                    public void onSuccess(Line data) {
                        DialogCTS2.dismiss();
                        selectedLine = data;
                        secondRecyclerView.setAdapter(new MySecondAdapter(selectedLine, letterLine));

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
                        DialogCTS2.show(getChildFragmentManager(), "onLoadingDialog");
                    }
                });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_horaire, container, false);


        // Add the following lines to create RecyclerView
        secondRecyclerView = view.findViewById(R.id.recyclerview_horaire);
        secondRecyclerView.setHasFixedSize(true);
        secondRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Button button = (Button) view.findViewById(R.id.buttonMap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ThirdActivity.class);
                intent.putExtra("textLineFromActivity2", selectedLine.getName());
                getActivity().startActivity(intent);
            }
        });


        return view;
    }
}