package com.shindra;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class ActivityLoad
{
    private AlertDialog loadPage;

    public ActivityLoad (Activity activity)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.activity_load, null));
        builder.setCancelable(false);
        loadPage = builder.create();
    }

    void showLoadingScreen()
    {
        loadPage.show();
    }

    void HideLoadingScreen()
    {
        loadPage.dismiss();
    }

}
