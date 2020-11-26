package com.shindra.ourTrams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R

class OurTramsAdapter(private var mOurTramsList: ArrayList<OurTramsItem>, private var mListener: OnItemClickListener) : RecyclerView.Adapter<OurTramsViewHolder>() {

    // Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OurTramsViewHolder {
        // Called to create each ViewHolder in RecyclerView
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cv_our_trams, parent, false)
        return OurTramsViewHolder(view)
    }

    override fun onBindViewHolder(holder: OurTramsViewHolder, position: Int) {
        // Called after onCreateViewHolder, to bind data for each ViewHolder in RecyclerView
        val item = mOurTramsList[position]
        holder.onBind(item, mListener)
    }

    override fun getItemCount(): Int {
        return mOurTramsList.size
    }

    fun setOurTramsList(list: ArrayList<OurTramsItem>)
    {
        // Update ViewHolders DataSet
        mOurTramsList = list
    }

    // Interface
    interface OnItemClickListener {
        // Interface to handle event (observer pattern)
        fun onButtonScheduleClick(tramLineName: String)
    }
}