package com.shindra;
import android.content.Context;
import android.view.LayoutInflater;
import androidx.appcompat.app.AlertDialog;

public class LoadingScreen extends AlertDialog{
    private AlertDialog alertDial;

    public LoadingScreen(Context context){
        super(context);
        Builder builder = new Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_screen, null));
        builder.setCancelable(false);
        alertDial = builder.create();
    }

    public void show()
    {
        alertDial.show();
    }

    public void dismiss()
    {
        alertDial.dismiss();
    }
}
