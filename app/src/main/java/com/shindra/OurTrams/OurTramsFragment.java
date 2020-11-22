package com.shindra.OurTrams;

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

import com.shindra.ErrorScreen;
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

    // Attributes
    private static final String TAG = "OurTramsFragment";
    private LoadingScreen mLoadingScreen;
    private RecyclerView mRecyclerView;
    private ErrorScreen mErrorScreen;

    // Methods
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView");
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle(getString(R.string.OurTrams));
        mLoadingScreen = new LoadingScreen(getContext());
        mErrorScreen = new ErrorScreen(getContext());
        // View configurations
        View view = inflater.inflate(R.layout.fv_our_trams, container, false);
        mRecyclerView = view.findViewById(R.id.fv_our_trams_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Adapter: list will automatically be updated
        OurTramsAdapter adapter = new OurTramsAdapter(new ArrayList<OurTramsItem>(), new OurTramsAdapter.OnItemClickListener() {
            @Override
            public void onButtonScheduleClick(String tramLineLetter) {
                Intent intent = new Intent(getActivity(), ScheduleActivity.class);
                intent.putExtra("tramLineLetter", tramLineLetter);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        Log.i(TAG, "onStart");
        super.onStart();
        ArrayList<OurTramsItem> ourTramsItemList = new ArrayList<OurTramsItem>();

        // Network calls
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                Log.i(TAG, "onLoading");
                mLoadingScreen.show();
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                Log.i(TAG, "onSuccess");
                mLoadingScreen.dismiss();

                // Get only tram data and convert it to OurTramsItem
                for (Line element : data){
                    if (element.getRouteType() == RouteType.TRAM){
                        ourTramsItemList.add(new OurTramsItem(element.getName()));
                    }
                }

                // Update view
                ((OurTramsAdapter) mRecyclerView.getAdapter()).SetOurTramsList(ourTramsItemList);
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