package com.shindra.TramSchedule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
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


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TramScheduleFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TramScheduleFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
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
            mParam1 = getArguments().getString(ARG_PARAM1);
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
}