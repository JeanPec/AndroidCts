package com.shindra.Stop;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.Line.ILineClickable;
import com.shindra.Line.LineAdapter;
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
    public RecyclerView stops;
    public  String lineName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_activity);

        Intent intent = getIntent();
        lineName = getIntent().getStringExtra("lineName");

        stops = findViewById(R.id.stops);
        stops.setLayoutManager(new LinearLayoutManager(getParent()));
        stops.setAdapter(new StopAdapter(new ArrayList<Stop>(), lineName));

        getSupportActionBar().setTitle(getTramLineNumber(lineName));
    }

    private int getTramLineNumber(String lineName)
    {
        switch (lineName)
        {
            case "Parc des Sports - Illkirch Graffenstaden":
                return R.string.lineA;
            case "Lingolsheim Tiergaertel - Hoenheim Gare":
                return R.string.lineB;
            case "Gare Centrale - Neuhof Rodolphe Reuss":
                return R.string.lineC;
            case "Poteries - Port du Rhin / Kehl Rathaus":
                return R.string.lineD;
            case "Robertsau l'Escale - Campus d'Illkirch":
                return R.string.lineE;
            case "Comtes - Place d'Islande":
                return R.string.lineF;
            default:
                return R.string.unknownLine;

        }
    }
}
