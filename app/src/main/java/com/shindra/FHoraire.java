package com.shindra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
    ArrayList<Stop> horaires = new ArrayList();

    public FHoraire() {
        // Required empty public constructor
    }


    public static FHoraire newInstance() {
        FHoraire Fragement = new FHoraire();

        Bundle args = new Bundle();

        Fragement.setArguments(args);

        return Fragement;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recycler = recycler.findViewById(R.id.RecylcerView);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);



        ObservableExtensionKt.convertToBehaviorSubject(model.lineWithEstimatedTimeTable(RouteType.TRAM,"#TODO",1));
    }

}