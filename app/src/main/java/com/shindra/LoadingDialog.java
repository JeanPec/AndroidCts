package com.shindra;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDialog {

    private AlertDialog loadDiag;

    public LoadingDialog (Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflateLay = activity.getLayoutInflater();
        builder.setView(inflateLay.inflate(R.layout.load_diag, null));
        builder.setCancelable(false);
        loadDiag = builder.create();
    }

    public void show(){
        loadDiag.show();
    }

    public void dismiss(){
        loadDiag.dismiss();
    }
}
