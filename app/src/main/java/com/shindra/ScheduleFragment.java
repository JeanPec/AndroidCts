package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.shindra.Misc.LoadingDialog;



public class ScheduleFragment extends Fragment {

    private Button map_button;
    private RecyclerView recyclerView;
    private String lineName;
    private ArrayList<Stop> stops = new ArrayList<Stop>();
    private LoadingDialog loadingDialog;

    public ScheduleFragment(String lineName, LoadingDialog loadingDialog){
        super();
        this.lineName= lineName;
        this.loadingDialog=loadingDialog;
    }
    private void setStops(Line data){
        for (Stop stop : data.getStops()) {
            if (stop.getEstimatedDepartureTime() != null) {
                stops.add(stop);
            }
        }
    }
    private  ArrayList<Stop> getStops(){
        return stops;
    }

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,
                            @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_schedule, container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.schedule_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ScheduleRecyclerViewAdapter(getStops(), lineName));
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(
                RouteType.TRAM, lineName,1), new ObservableListener<Line>() {
            @Override
            public void onSuccess(Line data) {
                loadingDialog.dismiss();
                setStops(data);
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                loadingDialog.dismiss();
            }

            @Override
            public void onError(@NotNull Throwable throwable) {

                //call if the network call has responded with an error
            }
        });

        map_button = view.findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                intent.putExtra("lineName",lineName);
                startActivity(intent);

            }
        });
    }
}
