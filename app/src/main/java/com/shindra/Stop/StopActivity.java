package com.shindra.Stop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.Map.MapActivity;
import com.shindra.Map.MapLineFragment;
import com.shindra.Misc.ErrorDialog;
import com.shindra.Misc.LoadingDialog;
import com.shindra.Misc.MyViewModel;
import com.shindra.R;
import com.shindra.Service.Converter;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StopActivity extends AppCompatActivity
{
    public RecyclerView stops;
    public Button seeOnMapButton;
    public  String lineName;
    public LoadingDialog loadingDialog;
    public ErrorDialog errorDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_activity);

        loadingDialog = new LoadingDialog(this);
        errorDialog = new ErrorDialog(this);

        Intent intent = getIntent();
        lineName = getIntent().getStringExtra("lineName");

        stops = findViewById(R.id.stops);
        stops.setLayoutManager(new LinearLayoutManager(getParent()));
        stops.setAdapter(new StopAdapter(new ArrayList<Stop>(), lineName));

        seeOnMapButton = findViewById(R.id.seeOnMapButton);
        seeOnMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(StopActivity.this, MapActivity.class);
                intent.putExtra("lineName",lineName);
                startActivity(intent);
            }
        });

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, Converter.lineNameToLineLetter(lineName), 0), new ObservableListener<Line>() {

            @Override
            public void onLoading()
            {
                //call once we started the network called. Indicate that the network call is in progress
                //Show the loading dialog
                loadingDialog.show();
            }

            @Override
            public void onSuccess(Line data)
            {
                //call once the network call has responded with a success
                //Dismiss the loading dialog
                loadingDialog.dismiss();

                //Set the right title to the app
                getSupportActionBar().setTitle("Ligne " + Converter.lineNameToLineLetter(lineName));

                //Only keep stops with a no-null departure time
                ArrayList<Stop> stopWithDeparture = new ArrayList<Stop>();
                for (Stop item : data.getStops())
                    if (item.getEstimatedDepartureTime() != null)
                        stopWithDeparture.add(item);

                //Update the recycler view through the adapter
                ((StopAdapter) stops.getAdapter()).setStops(stopWithDeparture);
                stops.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onError(@NotNull Throwable throwable)
            {
                //call if the network call has responded with an error
                //Dismiss the loading dialog and show the error dialog
                loadingDialog.dismiss();
                errorDialog.show();
            }
        });
    }
}
