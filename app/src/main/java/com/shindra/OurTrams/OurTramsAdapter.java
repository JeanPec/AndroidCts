package com.shindra.OurTrams;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;

import java.util.ArrayList;

public class OurTramsAdapter extends RecyclerView.Adapter<OurTramsViewHolder>{

    // Attributes
    private ArrayList<OurTramsItem> mOurTramsList;
    private OnItemClickListener mListener;

    // Constructor
    public OurTramsAdapter(ArrayList<OurTramsItem> arrayList, OnItemClickListener listener) {
        mOurTramsList = arrayList;
        mListener = listener;
    }

    // Methods
    @NonNull
    @Override
    public OurTramsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Called to create each ViewHolder in RecyclerView
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_our_trams, parent, false);
        OurTramsViewHolder tramVH = new OurTramsViewHolder(v);
        return tramVH;
    }
    @Override
    public void onBindViewHolder(@NonNull OurTramsViewHolder holder, int position) {
        // Called after onCreateViewHolder, to bind data for each ViewHolder in RecyclerView
        OurTramsItem currentItem = mOurTramsList.get(position);
        holder.onBind(currentItem, mListener);
    }
    @Override
    public int getItemCount() {
        return mOurTramsList.size();
    }
    public void SetOurTramsList(ArrayList<OurTramsItem> list)
    {
        mOurTramsList = list;
    }

    // Interface to handle event (observer pattern)
    public interface OnItemClickListener{
        void onButtonScheduleClick(String tramLineName);
    }
}
