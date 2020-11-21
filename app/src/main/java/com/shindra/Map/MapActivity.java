package com.shindra.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.shindra.LoadingScreen;
import com.shindra.MyViewModel;
import com.shindra.R;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

public class MapActivity extends AppCompatActivity {
    private static final String TAG = "MapActivity";
    private String tramLineLetter;
    private MapLineFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
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