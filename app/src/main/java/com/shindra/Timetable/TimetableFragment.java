package com.shindra.Timetable;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shindra.MyViewModel;
import com.shindra.NosTrams.NosTramsAdapter;
import com.shindra.R;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.sql.Time;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimetableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimetableFragment extends Fragment {

    private static final String ARG_PARAM1 = "LigneTram";
    private String ligneTram;
    private ArrayList<Stop> listArret = new ArrayList<>();
    RecyclerView recyclerVTimetable;



    // TODO: Rename and change types and number of parameters
    public static TimetableFragment newInstance(String ligneTram){
        TimetableFragment fragment = new TimetableFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, ligneTram);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ligneTram = this.getArguments().getString(ARG_PARAM1);

        View TimetableView = inflater.inflate(R.layout.fragment_timetable, container, false);
        recyclerVTimetable = TimetableView.findViewById(R.id.recyclerView_Timetable);
        recyclerVTimetable.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.Adapter timetableAdapter = new TimetableAdapter(listArret, ligneTram);
        recyclerVTimetable.setAdapter(timetableAdapter);
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM,ligneTram,0), new ObservableListener<Line>() {

            @Override
            public void onError(@NotNull Throwable throwable) {
                Log.i("Error","Appel réseau ne s'est pas fait correctement");
            }

            @Override
            public void onSuccess(Line data) {
                Log.i("Info","Appel réussi");

                listArret = new ArrayList<>();
                for (Stop Arrets : data.getStops())
                {
                    if (Arrets.getEstimatedDepartureTime() != null)
                    {
                        listArret.add(Arrets);
                    }
                    recyclerVTimetable.setAdapter(new TimetableAdapter(listArret,ligneTram));

                    Log.i("Info","OnStart Timetable?");
                }
            }

            @Override
            public void onLoading() {
                Log.i("Info","OnLoading Fragment Timetable");
            }
        });

        return TimetableView;
    }

}

