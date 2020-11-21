package com.shindra.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;

import com.shindra.R;

public class LoadingDialog extends AlertDialog {
    private AlertDialog loadingDialog;


    public LoadingDialog(Context context)
    {
        super(context);
    }


    public void startAnimation(){

    Builder builder = new Builder(getContext());
    LayoutInflater inflater = getLayoutInflater();
    builder.setView(inflater.inflate(R.layout.loading_dialog, null));
    builder.setCancelable(false);

    loadingDialog = builder.create();
    loadingDialog.show();

    }

    public void dismissDialog()
    {
        loadingDialog.dismiss();
    }
}
