package com.shindra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TramsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
         //Inflate the layout for this fragment
        val FragmentView = inflater.inflate(R.layout.fragment_trams, container, false);
        return FragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val _RecyclerView = view.findViewById(R.id.TramsRecyclerView) as RecyclerView;

        _RecyclerView.layoutManager = LinearLayoutManager(activity)
        _RecyclerView.adapter = TramsAdapters()
    }

}