package com.shindra.Schedule;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shindra.LoadingScreen;
import com.shindra.OurTrams.OurTramsAdapter;
import com.shindra.OurTrams.OurTramsItem;
import com.shindra.R;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {
    private static final String TAG = "ScheduleFragment";
    private RecyclerView mRecyclerView;
    private LoadingScreen mLoadingScreen;
    private Button buttonViewMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView");
        AppCompatActivity upper = (AppCompatActivity)getContext();
        String tramLineName = getArguments().getString("tramLineName");
        upper.getSupportActionBar().setTitle(tramLineName);
        mLoadingScreen = new LoadingScreen((Activity)getContext());

        // View configurations
        View view = inflater.inflate(R.layout.fv_schedule, container, false);
        mRecyclerView = view.findViewById(R.id.fv_schedule_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Adapter
        ScheduleAdapter adapter = new ScheduleAdapter(new ArrayList<ScheduleItem>());
        mRecyclerView.setAdapter(adapter);

        // Handle view map button
        buttonViewMap = view.findViewById(R.id.fv_schedule_buttonSeeToCard);
        buttonViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Appel la carte maps
            }
        });

        

        // Fake schedule Items
        ArrayList<ScheduleItem> scheduleList = new ArrayList<>();
        scheduleList.add(new ScheduleItem("Parc malraux","18H55","Tram A"));
        scheduleList.add(new ScheduleItem("Parc Bellevue","18H45","Tram B"));
        scheduleList.add(new ScheduleItem("Haguenau","20H55","Tram C"));
        scheduleList.add(new ScheduleItem("Strasbourg","21H55","Tram D"));
        scheduleList.add(new ScheduleItem("Oui","22H55","Tram E"));
        scheduleList.add(new ScheduleItem("Non","23H55","Tram F"));
        scheduleList.add(new ScheduleItem("Rome","19H55","Tram G"));
        scheduleList.add(new ScheduleItem("Planete","18H55","Tram H"));
        scheduleList.add(new ScheduleItem("Mars","17H55","Tram I"));
        scheduleList.add(new ScheduleItem("Humour","16H55","Tram J"));
        scheduleList.add(new ScheduleItem("Drole","15H55","Tram K"));

        Update(scheduleList);
        return view;
    }

    public void Update(ArrayList<ScheduleItem> scheduleList) {
        Log.i("OurTramsFragment", "UpdateMethod");
        ScheduleAdapter tmp = (ScheduleAdapter) mRecyclerView.getAdapter();
        tmp.SetScheduleList(scheduleList);

        // Recreate all ViewHolders
        tmp.notifyDataSetChanged();
    }
}