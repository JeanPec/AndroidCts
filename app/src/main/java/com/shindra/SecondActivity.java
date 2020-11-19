package com.shindra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

public class SecondActivity extends AppCompatActivity {

    private static final String TAGG = "SecondActivity";

    RecyclerView secondRecyclerView;

    TextView textTramLine,textTramDeparture;
    String StringTramLine; //store the line text clicked

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.horaire);
        setContentView(R.layout.activity_second);

        //StringTramLine will contain the Tram line letter (selected one)
        getDataStartActivity();
        //textTramLine.setText(StringTramLine);
        //setDataStartActivity();

        //FragmentHoraireRecycler();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame_recycler,new HoraireFragment(StringTramLine)).commit();


    }

    private void getDataStartActivity(){
        if(getIntent().hasExtra("textLine")){
            StringTramLine = getIntent().getStringExtra("textLine");
        }else{
            Toast.makeText(this,"No data passed", Toast.LENGTH_SHORT).show();
        }
    }

    private void setDataStartActivity(){
        textTramLine.setText(StringTramLine);
    }

    public void FragmentHoraireRecycler(){
        Fragment fragment = new Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_view_tag,fragment);
        fragmentTransaction.commit();

        switch (StringTramLine){
            case "A":

                break;
            case "B":

                break;
            case "C":

                break;
            case "D":

                break;
            case "E":

                break;
            case "F":

                break;
            default:

        }
    }


}

