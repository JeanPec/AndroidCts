package com.shindra

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragement.app.Fragement;

public class FragementA : Fragement {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        return inflater.inflate(R.layout.fragmenta, container)
    }
    @Override
    public View onViewCreated(view: View, savedInstabceState: Bundle?) {
        super.onViewCreated(view, savedInstabceState)

        view.findViewById<Button>(R.id.button).setOnClickListener {

        }
    }
}