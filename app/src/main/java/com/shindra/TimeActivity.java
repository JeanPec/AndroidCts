package com.shindra;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import java.util.Objects;

public class TimeActivity extends AppCompatActivity {

    private RecyclerView mTimeRecyclerView;
    private RecyclerView.Adapter mTimeAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent mIntent = getIntent();
        String mTramLine = mIntent.getStringExtra("LINE");
        setContentView(R.layout.fragment_time);
        setTitle("Ligne " + mTramLine);

        ArrayList<Stop> mStops = new ArrayList<>();

        mTimeRecyclerView = findViewById(R.id.TimeList);
        mTimeRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mTimeLayoutManager = new LinearLayoutManager(this);
        mTimeRecyclerView.setLayoutManager(mTimeLayoutManager);

        View mView = LayoutInflater.from(this).inflate(R.layout.progressbar_dialog, null);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this).setView(mView);
        AlertDialog mDialog = mBuilder.create();

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, mTramLine , 0), new ObservableListener<Line>() {

            @Override
            public void onLoading() {

                mDialog.show();
            }

            @Override
            public void onSuccess(Line pData) {

                mStops.addAll(Objects.requireNonNull(pData.getStops()));
                mTimeRecyclerView.setAdapter(mTimeAdapter);
                mDialog.dismiss();
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
            }
        });

        mTimeAdapter = new TimeRecyclerViewAdapter(mStops, mTramLine);
        mTimeRecyclerView.setAdapter(mTimeAdapter);
    }
}

