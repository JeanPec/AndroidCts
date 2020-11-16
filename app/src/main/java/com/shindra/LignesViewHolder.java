package com.shindra;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class LignesViewHolder extends RecyclerView.ViewHolder{
    protected TextView vName;
    protected TextView vSurname;
    protected TextView vTitle;

    public LignesViewHolder(View v) {
        super(v);
        vName = (TextView) v.findViewById(R.id.txtName);
        vSurname = (TextView) v.findViewById(R.id.txtSurName);
        vTitle = (TextView) v.findViewById(R.id.title);

    }
}
