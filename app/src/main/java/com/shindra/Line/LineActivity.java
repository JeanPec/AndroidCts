package com.shindra.Line;

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

import java.util.ArrayList;

public class LineActivity extends AppCompatActivity
{
    public LoadingDialog loadingDialog;
    public ErrorDialog errorDialog;
    public LineFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);

        loadingDialog = new LoadingDialog(this);
        errorDialog = new ErrorDialog(this);

        fragment = new LineFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameContainer, fragment).commit();

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>()
        {
            @Override
            public void onLoading()
            {
                //call once we started the network called. Indicate that the network call is in progress
                //Show the loading dialog
                loadingDialog.show();
            }

            @Override
            public void onSuccess(ArrayList<Line> data)
            {
                //call once the network call has responded with a success
                //Set the right title to the app
                getSupportActionBar().setTitle(R.string.lines);

                //Dismiss the loading dialog
                loadingDialog.dismiss();

                //Only keep Tram lines
                ArrayList<Line> tramLines = new ArrayList<Line>();
                for (Line item : data)
                    if (item.getRouteType() == RouteType.TRAM)
                        tramLines.add(item);

                //Update the recycler view through the adapter
                fragment.updateWidgets(tramLines);
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

