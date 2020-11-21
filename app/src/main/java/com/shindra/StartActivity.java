package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String LINENAME_MESSAGE = "com.souf.ctsfip3a.LINENAME";

    private ArrayList<Line> availableTramlines;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Change title of activity
        setTitle(getString(R.string.activity_name_trams));

        LoadingDialog loadingDialog = new LoadingDialog(this);

        RecyclerView recyclerView = SetUpRecyclerView();

        //Call api for available lines
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                Log.i(TAG, "Waiting for answer...");
                loadingDialog.showLoadingScreen();
            }

            @Override
            public void onSuccess(ArrayList<Line> receivedData) {
                //call once the network call has responded with a success
                Log.i(TAG, "Received data from network");
                loadingDialog.hideLoadingScreen();

                //Filter to get only trams
                for(Line l : receivedData) {
                    if(l.getRouteType() == RouteType.TRAM)
                        availableTramlines.add(l);
                }

                //Fill the recycler view with received tramlines
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
                Log.e(TAG, "API error");
            }
        });

    }

    private RecyclerView SetUpRecyclerView(){
        availableTramlines = new ArrayList<>(); //empty list to initialize recycler view

        RecyclerView recyclerView = findViewById(R.id.rvTramLines);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TramlinesRecyclerViewAdapter(availableTramlines, this::OnTramlineClick));
        return recyclerView;
    }

    public void OnTramlineClick(Line l){
        Log.i(TAG, "Clicked on the tramline with name : "+l.getName());

        Intent intent = new Intent(this, TramsSchedulesActivity.class);
        intent.putExtra(LINENAME_MESSAGE, l.getName());
        startActivity(intent);
    }
}

