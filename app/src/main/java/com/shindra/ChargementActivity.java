package com.shindra;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class ChargementActivity {
    private AlertDialog Page;

    public ChargementActivity (Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.activity_load,null));
        Page = builder.create();
    }

    void showChargement(){

        Page.show();
    }

    void hideChargement(){

        Page.dismiss();
    }
}
