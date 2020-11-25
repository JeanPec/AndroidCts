package com.shindra.TramSchedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.MyViewModel;
import com.shindra.R;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TramScheduleFrag extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String Line_Name;
    private String Line_Letter;
    private ArrayList<Stop> TramStopList;
    RecyclerView TramScheduleList;

    public TramScheduleFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TramScheduleFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static TramScheduleFrag newInstance(String Line) {
        TramScheduleFrag fragment = new TramScheduleFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, Line);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Line_Name = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Line_Letter = this.getArguments().getString(ARG_PARAM1);
        View ScheduleView = inflater.inflate(R.layout.recyclerview_tram_schedule, container, false);
        TramScheduleList = ScheduleView.findViewById(R.id.recyclerview_tram_schedule);
        TramScheduleList.setLayoutManager(new LinearLayoutManager(getContext()));
        return ScheduleView;
    }

    @Override
    public void onStart() {
        super.onStart();
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, Line_Letter, 0), new ObservableListener<Line>()
        {
            @Override
            public void onError(@NotNull Throwable throwable) {

            }

            @Override
            public void onLoading() {

            }

            public void onSuccess(Line data)
        {

                TramStopList = new ArrayList<Stop>();
        for (Stop listStop : data.getStops())
        {
            if (listStop.getEstimatedDepartureTime() != null)
            {
                TramStopList.add(listStop);
            }
            TramScheduleList.setAdapter(new RecyclerViewAdapter_Tram_schedule(TramStopList, Line_Letter));
        }
        }
        });
    }
}