package com.shindra;

import android.annotation.SuppressLint;
import android.view.View;

public class horaireOnClickListener implements View.OnClickListener {

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        v.setBackgroundColor(R.color.color_Ligne_A);
    }
}
