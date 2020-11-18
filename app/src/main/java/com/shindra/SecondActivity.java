package com.shindra;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    RecyclerView secondRecyclerView;
    TextView textTramLine; //store the view
    String StringTramLine; //store the line text clicked

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.horaire);
        setContentView(R.layout.activity_second);

        textTramLine = findViewById(R.id.LineLettre);
        getDataStartActivity();
        setDataStartActivity();

        //Frag();



       //secondRecyclerView = findViewById(R.id.RecyclerFragment);
        //com.shindra.MySecondAdapter myAdapter2 = new MySecondAdapter(this,StringTramLine);
        //secondRecyclerView.setAdapter(myAdapter2);
        //secondRecyclerView.setLayoutManager(new LinearLayoutManager(this));


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

public void Frag(){
    Fragment fragment = new Fragment();
    android.app.FragmentManager fragmentManager = getFragmentManager();
    android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.framlayout,fragment);
    fragmentTransaction.commit();
}


}

