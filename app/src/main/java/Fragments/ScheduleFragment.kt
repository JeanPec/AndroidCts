package Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.ScheduleAdapter
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop

class ScheduleFragment : Fragment() {

    private lateinit var fragmentView: View
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ScheduleAdapter
    private var listOfStop: MutableList<Stop> = mutableListOf()
    lateinit var lineTramName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ScheduleAdapter(lineTramName, listOfStop)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.schedule_fragment, container, false)
        recyclerView = fragmentView.findViewById(R.id.ScheduleRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        lineTramName = arguments?.getString("lineTramName")

        return fragmentView
    }

}