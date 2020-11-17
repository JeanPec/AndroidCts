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
    public OurTramsAdapter(ArrayList<OurTramsItem> arrayList) { mOurTramsList = arrayList; }

    // Methods
    @NonNull
    @Override
    public OurTramsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_our_trams, parent, false);
        OurTramsViewHolder tramVH = new OurTramsViewHolder(v, mListener);
        return tramVH;
    }
    @Override
    public void onBindViewHolder(@NonNull OurTramsViewHolder holder, int position) {
        OurTramsItem currentItem = mOurTramsList.get(position);
        holder.onBind(currentItem);
    }
    @Override
    public int getItemCount() {
        return mOurTramsList.size();
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    // Interface
    public interface OnItemClickListener{
        void onButtonScheduleClick(int position);
    }
}
