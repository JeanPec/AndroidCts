package com.shindra;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArretViewHolder extends RecyclerView.ViewHolder{
    public TextView mTextBodyLine;
    public TextView mTextBody2;
    public TextView mTextHeadline1;
    public TextView mTextTime;

    public ArretViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextBodyLine = itemView.findViewById(R.id.textBodyline);
        mTextBody2 = itemView.findViewById(R.id.textBody2);
        mTextHeadline1 = itemView.findViewById(R.id.textHeadline1);
        mTextTime = itemView.findViewById(R.id.textTime);
    }
}