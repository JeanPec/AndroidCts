package com.shindra.TramLines;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.MyViewModel;
import com.shindra.R;
import com.shindra.TramSchedule.TramScheduleActivity;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class TramLinesActivity extends AppCompatActivity
{
    private ArrayList<Line> tram_lines_list;
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.recyclerview_tram_lines);

        getSupportActionBar().setTitle(getString(R.string.nos_trams_label));

        RecyclerView Tram_Line_List = findViewById(R.id.recyclerview_tram_lines);
        Tram_Line_List.setLayoutManager(new LinearLayoutManager(this));
        //RecyclerView.Adapter LinesAdapter = new RecyclerViewAdapter_Tram_lines(tram_lines_list);

        Trams_View_Holder.ScheduleButtonClick callBack = new Trams_View_Holder.ScheduleButtonClick()
        {
            @Override
            public void onScheduleButtonClick(Line line)
            {
                Log.i(getString(R.string.nos_trams_label),"Appuie BTN : " + line.getName());
                //Creation de la nouvelle Intent
                Intent intent = new Intent(TramLinesActivity.this, TramScheduleActivity.class);
                intent.putExtra("Tram_Line",line.getName());
                startActivity(intent);
            }
        };




        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>()
        {
            @Override
            public void onLoading() {
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                tram_lines_list = new ArrayList<Line>();
                for (Line Tram_line : data)
                {
                    if(Tram_line.getRouteType() == RouteType.TRAM)
                    {
                        tram_lines_list.add(Tram_line);
                    }

                }
                Tram_Line_List.setAdapter(new RecyclerViewAdapter_Tram_lines(tram_lines_list));
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
            }
        });
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

}


