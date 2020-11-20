package com.shindra.Schedule;

import android.app.Activity;
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
import android.widget.TextView;

import com.shindra.CT;
import com.shindra.LoadingScreen;
import com.shindra.MyViewModel;
import com.shindra.OurTrams.OurTramsItem;
import com.shindra.R;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;

public class ScheduleFragment extends Fragment {
    private static final String TAG = "ScheduleFragment";
    private RecyclerView mRecyclerView;
    private LoadingScreen mLoadingScreen;
    private Button buttonViewMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView");
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Horaire");
        String tramLineLetter = getArguments().getString("tramLineLetter");
        mLoadingScreen = new LoadingScreen((Activity)getContext());

        // View configurations
        View view = inflater.inflate(R.layout.fv_schedule, container, false);
        mRecyclerView = view.findViewById(R.id.fv_schedule_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Adapter: scheduleList will automatically be updated with Update function
        ArrayList<ScheduleItem> scheduleList = new ArrayList<>();
        ScheduleAdapter adapter = new ScheduleAdapter(scheduleList);
        mRecyclerView.setAdapter(adapter);

        // Handle view map button
        buttonViewMap = view.findViewById(R.id.fv_schedule_buttonSeeToCard);
        buttonViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Appel la carte maps
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

                for (Stop element : data.getStops()){
                    try {
                        Date date = element.getEstimatedDepartureTime();
                        String dateFormatted = date.toString().substring(11,16).replace(':','h');
                        scheduleList.add(new ScheduleItem(tramLineLetter, element.getName(), dateFormatted));
                    }
                    catch (Exception e){
                        scheduleList.add(new ScheduleItem(tramLineLetter, element.getName(), "Terminus"));
                    }
                }
                UpdateView(scheduleList);
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                Log.i(TAG, "onError");
                Log.i(TAG, throwable.getCause().toString());
            }
        });

        return view;
    }

    public void UpdateView(ArrayList<ScheduleItem> scheduleList) {
        Log.i("ScheduleFragment", "UpdateMethod");
        ScheduleAdapter tmp = (ScheduleAdapter) mRecyclerView.getAdapter();
        tmp.SetScheduleList(scheduleList);

        // Recreate all ViewHolders
        tmp.notifyDataSetChanged();
    }
}