package com.shindra.Misc;

import android.app.Activity;
import android.view.LayoutInflater;
import androidx.appcompat.app.AlertDialog;
import com.shindra.R;

public class LoadingDialog
{
    private AlertDialog dialog;

    public LoadingDialog(Activity activity)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_loading, null));
        builder.setCancelable(false);
        dialog = builder.create();
    }

    public void show()
    {
        dialog.show();
    }

    public void dismiss()
    {
        dialog.dismiss();
    }
}
