package com.shindra;

import android.content.Intent;
import android.os.Bundle;
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
import android.view.LayoutInflater;
import android.app.AlertDialog;
import android.view.View;


public class StartActivity extends AppCompatActivity {

    private RecyclerView mTramRecyclerView;
    private RecyclerView.Adapter mTramAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tram);
        setTitle("Nos Trams");

        ArrayList<Line> mTramLines = new ArrayList<>();

        mTramRecyclerView = findViewById(R.id.TramList);
        mTramRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mTramLayoutManager = new LinearLayoutManager(this);
        mTramRecyclerView.setLayoutManager(mTramLayoutManager);

        View mView = LayoutInflater.from(this).inflate(R.layout.progressbar_dialog, null);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this).setView(mView);
        AlertDialog mDialog = mBuilder.create();

        Intent mIntent = new Intent(StartActivity.this, TimeActivity.class);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {

            @Override
            public void onLoading() {

                mDialog.show();
            }

            @Override
            public void onSuccess(ArrayList<Line> pData) {

               for (Line l : pData) {
                    if (l.getRouteType() == RouteType.TRAM) {
                        mTramLines.add(l);
                    }
                }
                mTramRecyclerView.setAdapter(mTramAdapter);
                mDialog.dismiss();
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
            }
        });

        mTramAdapter = new TramRecyclerViewAdapter(mTramLines, pLine -> {
            mIntent.putExtra("LINE", pLine.getName());
            startActivity(mIntent);
        });

        mTramRecyclerView.setAdapter(mTramAdapter);
    }
}