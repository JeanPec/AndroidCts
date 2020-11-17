package com.shindra;
import android.app.Activity;
import android.view.LayoutInflater;
import androidx.appcompat.app.AlertDialog;

public class LoadingScreen {
    private AlertDialog dialog;

    public LoadingScreen(Activity myActivity)
    {
        AlertDialog.Builder alertDialBuilder = new AlertDialog.Builder(myActivity);
        LayoutInflater inflater = myActivity.getLayoutInflater();
        alertDialBuilder.setView(inflater.inflate(R.layout.loading_screen, null));
        alertDialBuilder.setCancelable(false);
        dialog = alertDialBuilder.create();
    }

    public void display()
    {
        dialog.show();
    }

    public void dismiss()
    {
        dialog.dismiss();
    }
}
