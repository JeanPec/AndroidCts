package com.shindra;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDialog {
    private Activity activity;
    private AlertDialog loadingDialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    void startAnimation(){

    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    LayoutInflater inflater = activity.getLayoutInflater();
    builder.setView(inflater.inflate(R.layout.loading_dialog, null));
    builder.setCancelable(false);

    loadingDialog = builder.create();
    loadingDialog.show();

    }

    void dismissDialog()
    {
        loadingDialog.dismiss();
    }
}
