package com.shindra.OurTrams;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.shindra.R;

public class OurTramsActivity extends AppCompatActivity {
    private static final String TAG = "OurTramsActivity";
    private OurTramsFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.av_our_trams);

        // Create & call our fragment
        mFragment = new OurTramsFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.av_our_trams_fragmentHolder, mFragment).commit();
    }
}