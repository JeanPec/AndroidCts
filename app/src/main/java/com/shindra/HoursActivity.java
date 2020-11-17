package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HoursActivity extends AppCompatActivity {

    private TextView mTextView;

    private void HoursActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hourslayout);

        mTextView = (TextView) findViewById(R.id.text);

        // Enables Always-on
    }
}