package com.shindra.Stop;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.shindra.Misc.ErrorDialog;
import com.shindra.Misc.LoadingDialog;
import com.shindra.Misc.MyViewModel;
import com.shindra.R;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StopActivity extends AppCompatActivity
{
    public LoadingDialog loadingDialog;
    public ErrorDialog errorDialog;
    public StopFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_activity);

        loadingDialog = new LoadingDialog(this);
        errorDialog = new ErrorDialog(this);

        fragment = new StopFragment(getIntent().getStringExtra("lineName"));
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameContainer, fragment).commit();

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, fragment.getLineName(), 0), new ObservableListener<Line>() {

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
                //Set the right title to the app
                getSupportActionBar().setTitle("Ligne " + fragment.getLineName());

                //Dismiss the loading dialog
                loadingDialog.dismiss();

                //Only keep stops with a no-null departure time
                ArrayList<Stop> stopWithDeparture = new ArrayList<Stop>();
                for (Stop item : data.getStops())
                    if (item.getEstimatedDepartureTime() != null)
                        stopWithDeparture.add(item);

                //Update the recycler view through the adapter
                fragment.updateWidgets(stopWithDeparture);
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
