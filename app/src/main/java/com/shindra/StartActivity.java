package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
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

    private static final String TAG = "StartActivity";

    RecyclerView firstRecyclerView;;
    ArrayList<Line> dataCTS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.nosTram);
        setContentView(R.layout.activity_main);

        DialogFragment DialogCTS = new DialogProgress();

        firstRecyclerView = findViewById(R.id.RecyclerViewMain);
        firstRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

            ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
                @Override
                public void onLoading() {
                    //call once we started the network called. Indicate that the network call is in progress
                    Log.d(TAG, "waiting for answer");
                    //DialogCTS.dismiss();//reset before use
                    DialogCTS.show(getSupportFragmentManager(), "progressing bar");
                }

                @Override
                public void onSuccess(ArrayList<Line> dataRecieved) {
                    //call once the network call has responded with a success
                    Log.d(TAG, "on Success");

                    DialogCTS.dismiss();

                    //save data from tram lines
                    dataCTS = new ArrayList<Line>();
                    for (Line a : dataRecieved) {
                        if (a.getRouteType() == RouteType.TRAM)
                            dataCTS.add(a);
                    }
                    //set the recyclerview with the data from the API
                    firstRecyclerView.setAdapter(new MyAdapter(dataCTS, new MyAdapter.RecyclerHoraireClick() {
                        @Override
                        public void onHoraireClick(Line line) {
                            //if network is available open next activity
                            if (InternetConnection.checkConnection(getApplicationContext())) {
                                Log.d(TAG, "INTERNET IS AVAILABLE");
                                //open the SecondActivity with the intent
                                Intent intent = new Intent(StartActivity.this, SecondActivity.class);
                                intent.putExtra("textLine", line.getName());
                                StartActivity.this.startActivity(intent);
                            } else {
                                Log.d(TAG, "INTERNET IS NOT AVAILABLE");
                                Toast.makeText(getApplicationContext(),"Pas de connexion active", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }));
                }

                @Override
                public void onError(@NotNull Throwable throwable) {
                    //call if the network call has responded with an error
                    Log.d(TAG, "on Error");
                    //DialogCTS.dismiss();//reset before use
                    DialogCTS.show(getSupportFragmentManager(), "progressing bar");
                }
            });
    }
}

