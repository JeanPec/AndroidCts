package com.shindra;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


public class ScheduleActivity extends AppCompatActivity {

    String lineName;
    RecyclerView recyclerViewSchedule;
    Fragment fragmentSchedule;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_menu);


        Intent intent = getIntent();
        lineName = getIntent().getStringExtra("lineNameSelected");

        getSupportFragmentManager().beginTransaction().replace(R.id.fragScheduleContainer,new ScheduleFragment()).commit();




        //Intent intent = getIntent();
        //lineName = getIntent().getStringExtra("lineNameSelected");

        //Fragment scheduleFrag = new ScheduleFragment();
        //getSupportFragmentManager().beginTransaction().replace(R.id.scheduleMainMenu, scheduleFrag).commit();

        //allStops.add(new myStop(lineName,"ex","18h35"));


       /* recyclerViewSchedule = findViewById(R.id.recyclerViewFrag);
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSchedule.setAdapter(new RecyclerScheduleAdapter(allStops));

        APPEL RESEAU
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class); // recupere l'objet class d'un modele
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM , lineName , 0 , new ObservableListener<Line>() {
            @Override
            public void onError(@NotNull Throwable throwable) {

            }

            @Override
            public void onSuccess(Line stop) {

            }

            @Override
            public void onLoading() {

            }
        }*/


    }
}



