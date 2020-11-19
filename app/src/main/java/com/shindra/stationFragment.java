package com.shindra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class stationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewG, Bundle bundle)
    {
        return inflater.inflate(R.layout.fragment_station,viewG,false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view,bundle);
    }
}
