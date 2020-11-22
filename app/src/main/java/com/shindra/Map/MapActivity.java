package com.shindra.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.shindra.R;

public class MapActivity extends AppCompatActivity {

    // Attributes
    private static final String TAG = "MapActivity";
    private String tramLineLetter;
    private MapLineFragment mFragment;

    // Methods
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.av_map);

        // Retrieve intent data
        Intent intent = getIntent();
        tramLineLetter = intent.getStringExtra("tramLineLetter");

        // Create, pass args & call fragment
        mFragment = MapLineFragment.onInstance(tramLineLetter);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.av_map_fragmentHolder, mFragment).commit();
    }
}