package com.shindra

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HorairesFragment : Fragment() {

    public var StopsNames : MutableList<String> = ArrayList()
    public var DeparturesTimes : MutableList<String> = ArrayList()
    public lateinit var LineName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_horaires, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val _RecyclerView = view.findViewById(R.id.recylerview_horaires) as RecyclerView;

        _RecyclerView.layoutManager = LinearLayoutManager(activity)
        _RecyclerView.adapter = context?.let { HorairesAdapter(it,StopsNames,DeparturesTimes,LineName) }

        val MapButton = view.findViewById(R.id.buttonMap) as Button

        MapButton.setOnClickListener{
            val intent = Intent(context, MapActivity::class.java)
            intent.putExtra("Ligne",LineName);
            context?.startActivity(intent)
        }
    }
}