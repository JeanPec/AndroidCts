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

public class StartActivity extends AppCompatActivity {
    private ArrayList<Line> tramLines =  new ArrayList<Line>();

    private  ArrayList<Line> getListOfTramLines(){
        return tramLines;
    }
    private void setTramList(ArrayList<Line> lines){
        for(int i = 0; i < lines.size(); i++){
            if (lines.get(i).getRouteType() == RouteType.TRAM){
                tramLines.add(lines.get(i));

            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_lines);
        com.shindra.Misc.LoadingDialogForActivity loadingDialogForActivity = new com.shindra.Misc.LoadingDialogForActivity(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new LinesRecyclerViewAdapter(getListOfTramLines(), new RecyclerButtonClick() {
            @Override
            public void onLineClick(Line line) {

                Intent intent = new Intent(StartActivity.this, ScheduleActivity.class);
                intent.putExtra("lineName", line.getName());
                startActivity(intent);

            }
        }));

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                loadingDialogForActivity.show();
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {

                //call once the network call has responded with a success
                loadingDialogForActivity.dismiss();
                getSupportActionBar().setTitle(R.string.main_view_name);

                setTramList(data);
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onError(@NotNull Throwable throwable) {

                //call if the network call has responded with an error
            }
        });
    }


}

