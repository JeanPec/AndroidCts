package com.shindra.Schedule;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shindra.ErrorScreen;
import com.shindra.LoadingScreen;
import com.shindra.Map.MapActivity;
import com.shindra.MyViewModel;

import com.shindra.R;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ScheduleFragment extends Fragment {

    // Attributes
    private static final String TAG = "ScheduleFragment";
    private RecyclerView mRecyclerView;
    private LoadingScreen mLoadingScreen;
    private ErrorScreen mErrorScreen;
    private Button mButtonViewMap;
    private String mTramLineLetter;

    // Constructor
    public static ScheduleFragment onInstance(String tramLineLetter){
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putString("tramLineLetter", tramLineLetter);
        fragment.setArguments(args);
        return fragment;
    }

    // Methods
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView");
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle(getString(R.string.schedule));
        mTramLineLetter = getArguments().getString("tramLineLetter");
        mLoadingScreen = new LoadingScreen(getContext());
        mErrorScreen = new ErrorScreen(getContext());

        // View configurations
        View view = inflater.inflate(R.layout.fv_schedule, container, false);
        mRecyclerView = view.findViewById(R.id.fv_schedule_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Adapter: scheduleList will automatically be updated with Update function
        ScheduleAdapter adapter = new ScheduleAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(adapter);

        // Handle view map button
        mButtonViewMap = view.findViewById(R.id.fv_schedule_buttonSeeMap);
        mButtonViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                intent.putExtra("tramLineLetter", mTramLineLetter);
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onStart() {
        Log.i(TAG, "onStart");
        super.onStart();
        ArrayList<ScheduleItem> scheduleItemList = new ArrayList<ScheduleItem>();

        // Network calls
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, mTramLineLetter, 0), new ObservableListener<Line>() {
            @Override
            public void onLoading() {
                Log.i(TAG, "onLoading");
                mLoadingScreen.show();
            }

            @Override
            public void onSuccess(Line data) {
                Log.i(TAG, "onSuccess");
                mLoadingScreen.dismiss();

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                for (int i = 0; i < data.getStops().size(); i++) {
                    String tramStationName = data.getStops().get(i).getName();

                    if (tramStationName.length() > 30) {
                        tramStationName = tramStationName.substring(0, 28);
                        tramStationName += "..";
                    }

                    if (i == data.getStops().size() - 1) {
                        // Add the terminus one, which has no "departure time"
                        scheduleItemList.add(new ScheduleItem(mTramLineLetter, tramStationName, "Terminus"));
                        // Add a fake one to see the last one
                        scheduleItemList.add(new ScheduleItem("XXX", "XXX", "XXX"));
                        break;
                    }

                    String nextDepartureTime = sdf.format(data.getStops().get(i).getEstimatedDepartureTime()).replace(':', 'h');
                    scheduleItemList.add(new ScheduleItem(mTramLineLetter, tramStationName, nextDepartureTime));
                }

                // Update view
                ((ScheduleAdapter) mRecyclerView.getAdapter()).SetScheduleList(scheduleItemList);
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                Log.i(TAG, "onError");
                mLoadingScreen.dismiss();
                mErrorScreen.show();
            }
        });
    }
}