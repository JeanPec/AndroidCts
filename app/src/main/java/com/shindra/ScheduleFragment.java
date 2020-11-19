package com.shindra;

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

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String lineName;
    RecyclerView recyclerScheduleView;
    ArrayList<Stop> lineStops;
    Button seeOnMapButton;

    // TODO: Rename and change types of parameters
  //  private String mParam1;
    //private String mParam2;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScheduleFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduleFragment newInstance(String param1 , String param2) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1 , param1);
        args.putString(ARG_PARAM2 , param2);
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
        seeOnMapButton = view.findViewById(R.id.seeOnMapButton);
        seeOnMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapActivity = new Intent(getActivity(), MapActivity.class);
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
                    recyclerScheduleView.setAdapter(new RecyclerScheduleAdapter(lineStops,lineName));
                }
                loadingDialog.dismissDialog();
            }
            @Override
            public void onError(@NotNull Throwable throwable) {

            }

            @Override
            public void onLoading() {

            }
        });
    }
}