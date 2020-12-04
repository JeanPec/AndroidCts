package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
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

import io.reactivex.rxjava3.disposables.Disposable;

import static com.shindra.StartActivity.lineClicked;

public class HoraireActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Stop> stopArrayList;


   View fragment;
   Button map_button;


    protected void onStart() {
        super.onStart();

        fragment = findViewById(R.id.frag_horraire);
        map_button = findViewById(R.id.button_map);



        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HoraireActivity.this, MapActivity.class);

                startActivity(intent);
            }
        });


        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, lineClicked, 0), new ObservableListener<Line>() {

            @Override
            public void onSuccess(Line data) {

                fragment.setVisibility(View.INVISIBLE);



                stopArrayList = new ArrayList<Stop>();

                for(Stop stop : data.getStops()){

                    if (stop.getEstimatedDepartureTime() != null){
                        stopArrayList.add(stop);
                    }
                }
                recyclerView.setAdapter(new HoraireAdapter(stopArrayList, lineClicked));
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                fragment.setVisibility(View.INVISIBLE);

            }


            @Override
            public void onLoading() {

            }
        });


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire);

        getSupportActionBar().setTitle(getString(R.string.horaire_title));

        recyclerView = findViewById(R.id.appCts_recyclerview);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}