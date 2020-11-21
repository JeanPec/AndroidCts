package com.shindra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.shindra.arrakis.controls.MapFragment;

public class ThirdActivity extends AppCompatActivity {

    String stringTramLine; //use to store the line letter clicked

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.carte);
        setContentView(R.layout.activity_third);

        //take the selected line letter of the StartActivity
        getDataSecondActivity();
        setTitle("Ligne " + stringTramLine);


    }

    private void getDataSecondActivity(){
        if(getIntent().hasExtra("textLineFromActivity2")){
            stringTramLine = getIntent().getStringExtra("textLineFromActivity2");
        }else{
            Toast.makeText(this,"No data passed", Toast.LENGTH_SHORT).show();
        }
    }
}