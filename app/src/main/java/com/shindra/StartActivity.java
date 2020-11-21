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


public class StartActivity extends AppCompatActivity implements TramViewHolder.onButtonClickListener {

    RecyclerView rTramList;
    LoadingDialog loadDiag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ArrayList<Line> lTrams = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.Nos_trams);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        rTramList = findViewById(R.id.RecyclerView);
        rTramList.setLayoutManager(new LinearLayoutManager(this));
        rTramList.setAdapter(new RecyclerViewAdapter(lTrams, this));


        loadDiag = new LoadingDialog(this);




        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                loadDiag.show();
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
                loadDiag.dismiss();
                for (Line lTram : data){
                    if(lTram.getRouteType() == RouteType.TRAM){
                        lTrams.add(lTram);
                    }
                }
                ((RecyclerViewAdapter) rTramList.getAdapter()).setListTrams(lTrams);
                rTramList.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
                loadDiag.dismiss();
            }
        });


    }

    public void onButtonClick(Line iLine) {
        Intent intent = new Intent(this, HoursActivity.class);
        intent.putExtra("lineName", iLine.getName());
        startActivity(intent);
    }
}