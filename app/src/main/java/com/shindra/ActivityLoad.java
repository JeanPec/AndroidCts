package com.shindra;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;

public class ActivityLoad extends AlertDialog
{
    private AlertDialog loadPage;

    public ActivityLoad (Context context)
    {
        super(context);
    }

    void showLoadingScreen()
    {
        Builder builder = new Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.activity_load, null));
        builder.setCancelable(false);
        loadPage = builder.create();

        loadPage.show();
    }

    void HideLoadingScreen()
    {
        loadPage.dismiss();
    }

}
