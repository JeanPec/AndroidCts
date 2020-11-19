package com.shindra;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;


public class ScheduleActivity extends AppCompatActivity {

    String lineName;
    RecyclerView recyclerViewSchedule;
    Fragment fragmentSchedule;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_menu);
        setTitle(R.string.schedule_name);

//recupere le intent de StartActivity + recuperation de la ligne sélectionné
        Intent intent = getIntent();
        lineName = getIntent().getStringExtra("lineNameSelected");

        //passage en argument de la ligne sélectionné
        Bundle scheduleBundle = new Bundle();
        scheduleBundle.putString("lineName",lineName);
        fragmentSchedule = new ScheduleFragment();
        fragmentSchedule.setArguments(scheduleBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragScheduleContainer,fragmentSchedule).commit();


        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class); // recupere l'objet class d'un modele
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM , lineName , 0), new ObservableListener<Line>()
        {

            @Override
            public void onError(@NotNull Throwable throwable) {

            }

            @Override
            public void onSuccess(Line data) {

            }

            @Override
            public void onLoading() {

            }
        });
    }
}



