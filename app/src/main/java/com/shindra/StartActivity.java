package com.shindra;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.util.StringUtil;

import com.shindra.arrakis.bo.Persistant;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.Lines;
import com.shindra.ctslibrary.apibo.LinesDelivery;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Observable;


public class StartActivity extends AppCompatActivity {

    private static final String TAG = "StartActivity";

    RecyclerView firstRecyclerView;;
    ArrayList<Line> dataCTS;
    String fromAPI[];
    int NbLinesAvailable = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.nosTram);
        setContentView(R.layout.activity_main);

        //DialogFragment DialogCTS = new DialogProgress();

        firstRecyclerView = findViewById(R.id.RecyclerView1);
        firstRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                Log.d(TAG,"waiting for answer");
                //DialogCTS.show(getSupportFragmentManager(), "progressing bar");
            }

            @Override
            public void onSuccess(ArrayList<Line> dataRecieved) {
                //call once the network call has responded with a success
                Log.d(TAG,"on Success");
                dataCTS = new ArrayList<Line>();
                for(Line a : dataRecieved){
                    if(a.getRouteType()== RouteType.TRAM)
                        dataCTS.add(a);
                }
               /*
               for (int j = 0; j < dataCTS.size() ; j++) {
                   linesFromAPI[j] = dataCTS.get(j).getName().toString();
                }
                fromAPI[0] = dataCTS.get(0).getName();
                fromAPI[1] = dataCTS.get(1).getName();
                fromAPI[2] = dataCTS.get(2).getName();
                fromAPI[3] = dataCTS.get(3).getName();
                fromAPI[4] = dataCTS.get(4).getName();
                fromAPI[5] = dataCTS.get(5).getName();
                */

                Log.d(TAG,"Name 1: " + fromAPI);
                Log.d(TAG,"Name 1: " + dataCTS);

                firstRecyclerView.setAdapter(new MyAdapter(dataCTS.size()));
                //DialogCTS.dismiss();

            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
                Log.d(TAG,"on Error");
            }
        });


    }

}

