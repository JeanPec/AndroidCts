package com.shindra;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

// stores and recycles views as they are scrolled off screen
public class TramlineViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "tramlineViewHolder";

    TextView lineName;

    TramlineViewHolder(View itemView) {
        super(itemView);
        Log.i(TAG,"constructor");

        lineName = itemView.findViewById(R.id.cvTramLine_Name);
    }

    public void onBind(Line l) {
        Log.i(TAG,"onBind : "+lineName.getText());

        lineName.setText(l.getName());
    }

}