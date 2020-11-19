package com.shindra;

import android.content.Context;
import android.content.Intent;
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
import java.util.ArrayList;


public class StartActivity extends AppCompatActivity {

    private static final String TAG = "StartActivity";

    RecyclerView firstRecyclerView;;
    ArrayList<Line> dataCTS;

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
                //DialogCTS.dismiss();//reset before use
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
                Log.d(TAG,"Nb of tram : " + dataCTS.size());
                Log.d(TAG,"Line TRAM : " + dataCTS);

                firstRecyclerView.setAdapter(new MyAdapter(dataCTS.size(), dataCTS, new MyAdapter.RecyclerHoraireClick() {
                    @Override
                    public void onHoraireClick(Line line) {
                        Intent intent = new Intent(StartActivity.this,SecondActivity.class);
                        //dynamicaly get data from the string array
                        intent.putExtra("textLine", line.getName());
                        //intent.putExtra("imagesTramLine",imagesTramLine);
                        StartActivity.this.startActivity(intent); //open the TimeActivity with the intent
                    }
                }));
                //DialogCTS.dismiss();

            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
                Log.d(TAG,"on Error");
                //DialogCTS.dismiss();//reset before use
                //DialogCTS.show(getSupportFragmentManager(), "progressing bar");
            }
        });


    }
}

