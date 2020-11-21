package com.shindra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.shindra.arrakis.controls.MapFragment;

/*
    This activity displays the google map plan
*/

public class ThirdActivity extends AppCompatActivity {

    String stringTramLine; //use to store the line letter clicked
    int clickedLineColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.carte);
        setContentView(R.layout.activity_third);

        //take the selected line letter of the StartActivity
        getDataSecondActivity();
        setTitle("Ligne " + stringTramLine);


        FragmentTransaction ftr = getSupportFragmentManager().beginTransaction();
        //pass the line letter corresponding to the line we clicked on
        ftr.add(R.id.frame_map, new MapPointFragment(stringTramLine,clickedLineColor)).commit();


    }

    private void getDataSecondActivity(){
        if(getIntent().hasExtra("textLineFromActivity2") || getIntent().hasExtra("lineColor")){
            stringTramLine = getIntent().getStringExtra("textLineFromActivity2");
            clickedLineColor = getIntent().getIntExtra("lineColor",0);

        }else{
            Toast.makeText(this,"No data passed", Toast.LENGTH_SHORT).show();
        }
    }
}