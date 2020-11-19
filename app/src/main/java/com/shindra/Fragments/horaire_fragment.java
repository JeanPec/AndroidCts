package com.shindra.Fragments;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shindra.Activites.HoraireAdaptater;
import com.shindra.Activites.MyViewModel;
import com.shindra.Activites.TrameStop;
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
 * Use the {@link horaire_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class horaire_fragment extends Fragment {

    public View fragmentview;
    public horaire_fragment() { }
    private RecyclerView rcView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentview = inflater.inflate(R.layout.fragment_horaire_fragment, container, false);

        return fragmentview;
    }

    @Override
    public void onStart() {
        super.onStart();
        rcView = fragmentview.findViewById(R.id.recycler_view_horaire);
        rcView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rcView.setLayoutManager(new LinearLayoutManager(this.getContext()));

            MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

            int direction = 0;
            RouteType routeType = null;

            ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(routeType.TRAM, mParam1, 0), new ObservableListener<Line>() {
                @Override
                public void onSuccess(Line data) {
                    ArrayList<TrameStop> TramesStop = new ArrayList<>();
                    for(Stop lineStop :  data.getStops() ) {
                        TramesStop.add(new TrameStop(lineStop.getName(),data.getName(),lineStop.getEstimatedDepartureTime()));
                    }
                    rcView.setAdapter(new HoraireAdaptater(TramesStop));
                }

                @Override
                public void onError(@NotNull Throwable throwable) {

                }

                @Override
                public void onLoading() {

                }
            });

            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
              /*  mParam2 = getArguments().getString(ARG_PARAM2);
                mParam3 = getArguments().getString(ARG_PARAM3);
                mParam4 = getArguments().getString(ARG_PARAM4);*/
            }


        }


    @Override
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcView = view.findViewById(R.id.recycler_view_horaire);
        rcView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param3";

    // TODO: Rename and change types of parameters
    private String mParam1;
   /* private String mParam2;
    private String mParam3;
    private String mParam4;*/


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment horaire_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static horaire_fragment newInstance(String param1) {
        horaire_fragment fragment = new horaire_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
       /* args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);*/
        fragment.setArguments(args);
        return fragment;
    }



}