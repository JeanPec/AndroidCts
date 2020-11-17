package com.shindra.Misc;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import com.shindra.R;

public class ErrorDialog
{
    private AlertDialog dialog;
    private View dialogView;
    private Button buttonAccept;

    public ErrorDialog(Activity activity)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_error, null);
        builder.setView(dialogView);
        builder.setCancelable(false);
        dialog = builder.create();

        buttonAccept = dialogView.findViewById(R.id.buttonAccept);
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dismiss();
                activity.finish();
            }
        });
    }

    public void show()
    {
        dialog.show();
    }

    private void dismiss()
    {
        dialog.dismiss();
    }
}
