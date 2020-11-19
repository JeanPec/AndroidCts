package com.shindra;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class loadingWindow {

    Activity _activity;
    AlertDialog _loadingWindow;

    public loadingWindow(Activity activity)
    {
        _activity=activity;
    }

    public void displayLoadingWindow()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(_activity);

        LayoutInflater inflater = _activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.cardview_loading_window,null));
        builder.setCancelable(true);

        _loadingWindow = builder.create();
        _loadingWindow.show();
    }

    public void dismissLoadingWindow()
    {
        _loadingWindow.dismiss();
    }
}
