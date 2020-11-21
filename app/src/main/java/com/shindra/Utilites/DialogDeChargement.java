package com.shindra.Utilites;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;

import com.shindra.R;

public class DialogDeChargement extends AlertDialog
{
    public AlertDialog PageChargement;

    public DialogDeChargement (Context context)
    {
        super(context);
    }

    public void AfficherPageChargement()
    {
        Builder builder = new Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_view, null));
        builder.setCancelable(false);

        PageChargement=builder.create();
        PageChargement.show();
    }

    public void EnleverPageChargement()
    {
        PageChargement.dismiss();
    }
}