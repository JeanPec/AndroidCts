package com.shindra.Schedule;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.Dialog.LoadingDialog;
import com.shindra.Map.MyMapActivity;
import com.shindra.MyViewModel;
import com.shindra.R;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class MyScheduleFragment extends Fragment {

    String lineName;
    RecyclerView recyclerScheduleView;
    ArrayList<Stop> lineStops;
    Button seeOnMapButton;

    public MyScheduleFragment() {
        // Required empty public constructor
    }

    public static MyScheduleFragment newInstance(String lineName) {
        MyScheduleFragment fragment = new MyScheduleFragment();
        Bundle args = new Bundle();
        args.putString("lineName" , lineName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         lineName = this.getArguments().getString("lineName");
        View view = inflater.inflate(R.layout.schedule_frag, container , false);
        recyclerScheduleView = view.findViewById(R.id.recyclerViewScheduleFrag);
        recyclerScheduleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerScheduleView.setAdapter(new MyRecyclerScheduleAdapter(new ArrayList<Stop>(),lineName,getString(R.string.map_name)));
        seeOnMapButton = view.findViewById(R.id.seeOnMapButton);



        seeOnMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapActivity = new Intent(getActivity(), MyMapActivity.class);
                mapActivity.putExtra("lineNameSelected", lineName);
                startActivity(mapActivity);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        LoadingDialog loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.startAnimation();

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class); // recupere l'objet class d'un modele
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM , lineName , 0) , new ObservableListener<Line>() {

            @Override
            public void onSuccess(Line data) {
                lineStops = new ArrayList<Stop>();
                for (Stop oneStop : data.getStops()) {
                    if (oneStop.getEstimatedDepartureTime() != null) {

                        lineStops.add(oneStop);
                    }
                    ((MyRecyclerScheduleAdapter)recyclerScheduleView.getAdapter()).setListStop(lineStops);
                    recyclerScheduleView.getAdapter().notifyDataSetChanged();
                }

                loadingDialog.dismissDialog();
            }
            @Override
            public void onError(@NotNull Throwable throwable) {
                loadingDialog.dismissDialog();
            }

            @Override
            public void onLoading() {

            }
        });
    }
}