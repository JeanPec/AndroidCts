package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ArretAdapter extends RecyclerView.Adapter<ArretAdapter.ArretViewHolder>{
    private ArrayList<ArretItem> mArretList;
    private ArretViewHolder mArretViewHolder;

    public static class ArretViewHolder extends RecyclerView.ViewHolder{
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

    public ArretAdapter(ArrayList<ArretItem> arrayList){
        mArretList = arrayList;
    }

    // Ctrl + i to see missing methods which need to be override
    @NonNull
    @Override
    public ArretViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_arret, parent, false);
        ArretViewHolder evh = new ArretViewHolder(v);
        return evh;
    }

    // Pass values
    @Override
    public void onBindViewHolder(@NonNull ArretViewHolder holder, int position) {
        ArretItem currentItem = mArretList.get(position);
        holder.mTextBody2.setText(currentItem.getmTextBody2());
        holder.mTextBodyLine.setText(currentItem.getmTextBodyLine());
        holder.mTextHeadline1.setText(currentItem.getmTextHeadline1());
        holder.mTextTime.setText(currentItem.getmTextTime());
    }

    @Override
    public int getItemCount() {
        return mArretList.size();
    }
}
