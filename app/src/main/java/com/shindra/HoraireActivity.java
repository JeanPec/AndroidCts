package com.shindra;

import android.os.Bundle;
//import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HoraireActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire);

        mTextView = (TextView) findViewById(R.id.text);

        // Enables Always-on
        //setAmbientEnabled();
    }
}