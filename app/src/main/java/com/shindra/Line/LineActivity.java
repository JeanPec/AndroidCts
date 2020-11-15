package com.shindra.Line;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.Misc.ErrorDialog;
import com.shindra.Misc.LoadingDialog;
import com.shindra.Misc.MyViewModel;
import com.shindra.R;
import com.shindra.Stop.StopActivity;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.Coordinate;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;

public class LineActivity extends AppCompatActivity
{
    public RecyclerView lines;
    public LoadingDialog loadingDialog;
    public ErrorDialog errorDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_activity);

        loadingDialog = new LoadingDialog(this);
        errorDialog = new ErrorDialog(this);

        lines = findViewById(R.id.lines);
        lines.setLayoutManager(new LinearLayoutManager(this));
        lines.setAdapter(new LineAdapter(new ArrayList<Line>(), new ILineClickable() {
            @Override
            public void OnLineClick(Line line)
            {
                //Launch the stop activity here
                Intent intent = new Intent(LineActivity.this, StopActivity.class);
                intent.putExtra("lineName", line.getName());
                startActivity(intent);
            }
        }));

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
                //Dismiss the loading dialog
                loadingDialog.dismiss();

                //Set the right title to the app
                getSupportActionBar().setTitle(R.string.lines);

                //Only keep Tram lines
                ArrayList<Line> tramLines = new ArrayList<Line>();
                for (Line item : data)
                    if (item.getRouteType() == RouteType.TRAM)
                        tramLines.add(item);

                //Update the recycler view through the adapter
                ((LineAdapter) lines.getAdapter()).setLines(tramLines);
                lines.getAdapter().notifyDataSetChanged();
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

