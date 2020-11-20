package com.shindra.OurTrams;

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

import com.shindra.LoadingScreen;
import com.shindra.MyViewModel;
import com.shindra.R;
import com.shindra.Schedule.ScheduleActivity;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OurTramsFragment extends Fragment {
    private static final String TAG = "OurTramsFragment";
    private LoadingScreen mLoadingScreen;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView");
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Nos Trams");
        mLoadingScreen = new LoadingScreen((Activity)getContext());

        // View configurations
        View view = inflater.inflate(R.layout.fv_our_trams, container, false);
        mRecyclerView = view.findViewById(R.id.fv_our_trams_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Adapter: list will automatically be update with Update method
        OurTramsAdapter adapter = new OurTramsAdapter(new ArrayList<OurTramsItem>(), new OurTramsAdapter.OnItemClickListener() {
            @Override
            public void onButtonScheduleClick(String tramLineLetter) {
                Intent intent = new Intent(getActivity(), ScheduleActivity.class);
                intent.putExtra("tramLineLetter", tramLineLetter);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapter);

        // Network calls
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                Log.i(TAG, "onLoading");
                mLoadingScreen.display();
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                Log.i(TAG, "onSuccess");
                mLoadingScreen.dismiss();

                // Get only tram data and convert it to OurTramsItem
                ArrayList<OurTramsItem> ourTramsItemList = new ArrayList<OurTramsItem>();
                for (Line element : data){
                    if (element.getRouteType() == RouteType.TRAM){
                        ourTramsItemList.add(new OurTramsItem(element.getName()));
                    }
                }

                UpdateView(ourTramsItemList);
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                Log.i(TAG, "onError");
                mLoadingScreen.dismiss();
            }
        });
        return view;
    }

    public void UpdateView(ArrayList<OurTramsItem> ourTramsItemsList) {
        Log.i("OurTramsFragment", "UpdateMethod");
        OurTramsAdapter tmp = (OurTramsAdapter) mRecyclerView.getAdapter();
        tmp.SetOurTramsList(ourTramsItemsList);

        // Recreate all ViewHolders
        tmp.notifyDataSetChanged();
    }
}