package com.shindra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;


public class SecondActivity extends AppCompatActivity {

    String StringTramLine; //use to store the line letter clicked

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.horaire);
        setContentView(R.layout.activity_second);

        //take the selected line letter of the StartActivity
        getDataStartActivity();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //pass the line letter corresponding to the line we clicked on
        ft.add(R.id.frame_recycler,new HoraireFragment(StringTramLine)).commit();

    }

    private void getDataStartActivity(){
        if(getIntent().hasExtra("textLine")){
            StringTramLine = getIntent().getStringExtra("textLine");
        }else{
            Toast.makeText(this,"No data passed", Toast.LENGTH_SHORT).show();
        }
    }
}

