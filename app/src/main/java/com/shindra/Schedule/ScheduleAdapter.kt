package com.shindra.Schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R

class ScheduleAdapter(private var mScheduleList: ArrayList<ScheduleItem>) : RecyclerView.Adapter<ScheduleViewHolder>() {

    // Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        // Called to create each ViewHolder in RecyclerView
        var view = LayoutInflater.from(parent.context).inflate(R.layout.cv_schedule, parent, false)
        return ScheduleViewHolder(view);
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        // Called after onCreateViewHolder, to bind data for each ViewHolder in RecyclerView
        var item = mScheduleList[position]
        holder.onBind(item, holder.itemView.context)
    }

    override fun getItemCount(): Int {
        return mScheduleList.size
    }

    fun SetScheduleList(list: ArrayList<ScheduleItem>)
    {
        // Update ViewHolders DataSet
        mScheduleList = list;
        notifyDataSetChanged();
    }
}