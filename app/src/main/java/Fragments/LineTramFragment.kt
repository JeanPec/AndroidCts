package Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.LineTramAdapter
import com.shindra.LineTramViewHolder
import com.shindra.R
import com.shindra.ctslibrary.apibo.Coordinate
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import java.util.*

class LineTramFragment : Fragment() {

    private lateinit var fragmentView: View
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: LineTramAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateComponent()
    }

    private fun onCreateComponent() {
        adapter = LineTramAdapter(listOfLines)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_line_tram, container, false)
        initializeRecyclerView()
        return fragmentView
    }

    private fun initializeRecyclerView() {
        recyclerView = fragmentView.findViewById(R.id.LineTramRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    private val listOfStops = arrayListOf<Stop>(
        Stop("Campus Illkirch", Date(), Date(), "Parc des sports", Coordinate()),
        Stop("Général Leclerc", Date(), Date(), "Parc des sports", Coordinate()),
        Stop("Colonne", Date(), Date(), "Parc des sports", Coordinate()),
        Stop("Baggersee", Date(), Date(), "Parc des sports", Coordinate())
    )

    private val listOfLines = listOf(
        Line("A", RouteType.TRAM, listOfStops),
        Line("B", RouteType.TRAM, listOfStops),
        Line("C", RouteType.TRAM, listOfStops),
        Line("D", RouteType.TRAM, listOfStops),
        Line("E", RouteType.TRAM, listOfStops),
        Line("F", RouteType.TRAM, listOfStops)
    )
}