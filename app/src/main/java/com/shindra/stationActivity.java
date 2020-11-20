package com.shindra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

public class stationActivity extends AppCompatActivity {

    private stationFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_diary);
        getSupportActionBar().setTitle(getString(R.string.stationActivityName));

        frag = new stationFragment();
        Bundle bundle = new Bundle();
        Intent intentFromStartActivity = getIntent();
        bundle.putString("lineName",intentFromStartActivity.getStringExtra("TRAM_LINE"));
        frag.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().add(R.id.container_station,frag).commit();
        loadingWindow window = new loadingWindow(stationActivity.this);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM,intentFromStartActivity.getStringExtra("TRAM_LINE"),0), new ObservableListener<Line>() {

            @Override
            public void onError(@NotNull Throwable throwable) {

            }

            @Override
            public void onSuccess(Line data) {
                window.dismissLoadingWindow();

                RecyclerView.Adapter _adapter = new stationAdapter(data);
                frag.recycler.setAdapter(_adapter);

                Button buttonToMapStation = (Button) findViewById(R.id.buttonToMapActivity);
                buttonToMapStation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(stationActivity.this, mapActivity.class);
                        intent.putExtra("LINE_NAME",data.getName());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onLoading() {
                window.displayLoadingWindow();
            }
        });
    }


}