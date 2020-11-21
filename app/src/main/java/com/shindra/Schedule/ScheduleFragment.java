package com.shindra.Schedule;

import android.app.Activity;
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
import com.shindra.Map.MapLineFragment;
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
    private static final String TAG = "ScheduleFragment";
    private RecyclerView mRecyclerView;
    private LoadingScreen mLoadingScreen;
    private ErrorScreen mErrorScreen;
    private Button mButtonViewMap;

    public static ScheduleFragment onInstance(String tramLineLetter){
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putString("tramLineLetter", tramLineLetter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView");
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle(getString(R.string.schedule));
        String tramLineLetter = getArguments().getString("tramLineLetter");
        mLoadingScreen = new LoadingScreen((Activity)getContext());
        mErrorScreen = new ErrorScreen((Activity)getContext());

        // View configurations
        View view = inflater.inflate(R.layout.fv_schedule, container, false);
        mRecyclerView = view.findViewById(R.id.fv_schedule_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Adapter: scheduleList will automatically be updated with Update function
        ArrayList<ScheduleItem> scheduleList = new ArrayList<>();
        ScheduleAdapter adapter = new ScheduleAdapter(scheduleList);
        mRecyclerView.setAdapter(adapter);

        // Handle view map button
        mButtonViewMap = view.findViewById(R.id.fv_schedule_buttonSeeMap);
        mButtonViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                intent.putExtra("tramLineLetter",tramLineLetter);
                startActivity(intent);
            }
        });

        // Network calls
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, tramLineLetter, 0), new ObservableListener<Line>() {@Override
        public void onLoading() {
            Log.i(TAG, "onLoading");
            mLoadingScreen.display();
        }

            @Override
            public void onSuccess(Line data) {
                Log.i(TAG, "onSuccess");
                mLoadingScreen.dismiss();

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                for(int i=0; i<data.getStops().size();i++)
                {
                    String tramStationName = data.getStops().get(i).getName();
                    if(tramStationName.length() > 30)
                    {
                        tramStationName = data.getStops().get(i).getName().substring(0, 27);
                        tramStationName += "...";
                    }

                    if(i==data.getStops().size()-1){
                        // Add the terminus one, which has no "departure time"
                        scheduleList.add(new ScheduleItem(tramLineLetter, tramStationName, "Terminus"));
                        // Add a fake one to see the last one
                        scheduleList.add(new ScheduleItem("XXX", "XXX", "XXX"));
                        break;
                    }

                    String nextDepartureTime = sdf.format(data.getStops().get(i).getEstimatedDepartureTime()).replace(':','h');
                    scheduleList.add(new ScheduleItem(tramLineLetter, tramStationName, nextDepartureTime));
                }

                UpdateView(scheduleList);
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                Log.i(TAG, "onError");
                mLoadingScreen.dismiss();
                mErrorScreen.display();
            }
        });

        return view;
    }

    public void UpdateView(ArrayList<ScheduleItem> scheduleList) {
        Log.i(TAG, "UpdateMethod");
        ScheduleAdapter tmp = (ScheduleAdapter) mRecyclerView.getAdapter();
        tmp.SetScheduleList(scheduleList);
    }
}