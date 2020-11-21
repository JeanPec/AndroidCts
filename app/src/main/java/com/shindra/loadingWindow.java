package com.shindra;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;

public class loadingWindow extends Dialog{

    Activity _activity;
    AlertDialog _loadingWindow;

    protected loadingWindow(Context context) {
        super(context);
    }


    public void displayLoadingWindow()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getLayoutInflater();
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
