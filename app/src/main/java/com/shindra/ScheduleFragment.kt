package com.shindra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

private const val ARG_PARAM1 = "line"

class ScheduleFragment : Fragment() {

    private var line : String? = null
    private var lineObject : Line? = null
    private lateinit var scheduleCardViewAdapter : ScheduleCardViewAdapter
    private var recyclerView : RecyclerView? = null


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            line = it.getString(ARG_PARAM1)!!
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.scheduleRecyclerView)
        var mapButton : Button = view.findViewById(R.id.mapButton)

        mapButton.setOnClickListener{

            val lineMapFragment = LineMapFragment.newInstance(line)
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout, lineMapFragment)?.addToBackStack("tag")
            transaction?.setReorderingAllowed(true)
            transaction?.commit()
        }


    }

    override fun onStart() {
        super.onStart()
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, line!!, 0).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress

            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success
                lineObject = data
                scheduleCardViewAdapter = ScheduleCardViewAdapter(lineObject, object : com.shindra.RecyclerItemClick {
                    override fun onScheduleClick(tram: Line) {
                        Toast.makeText(context, tram.name.toString(), Toast.LENGTH_SHORT).show()
                    }
                })
                scheduleCardViewAdapter.setStops(lineObject)
                scheduleCardViewAdapter.notifyDataSetChanged()
                recyclerView?.layoutManager = LinearLayoutManager(activity)
                recyclerView?.adapter = scheduleCardViewAdapter
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param line Our current tram line.
         * @return A new instance of fragment ScheduleFragment.
         */
        @JvmStatic
        fun newInstance(line: String?)=
                ScheduleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, line)
                }
            }
    }

}