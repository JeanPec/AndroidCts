package com.shindra.Map;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import org.jetbrains.annotations.NotNull;

public class MapActivity extends AppCompatActivity
{
    public String lineName;
    public LoadingDialog loadingDialog;
    public ErrorDialog errorDialog;
    public MapLineFragment fragment;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        fragment = new MapLineFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameContainer, fragment).commit();

        loadingDialog = new LoadingDialog(this);
        errorDialog = new ErrorDialog(this);

        Intent intent = getIntent();
        lineName = getIntent().getStringExtra("lineName");

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithStop(RouteType.TRAM, lineName), new ObservableListener<Line>() {

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
                getSupportActionBar().setTitle("Ligne " + lineName);

                //Add Stops icon on map
                fragment.addStopsOnMap(data);
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
