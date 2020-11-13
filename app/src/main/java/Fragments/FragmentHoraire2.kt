package Fragments

import android.graphics.Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R

data class Movie(val title: String, val year: Int)

class FragmentHoraire2 : Fragment() {

    private var recyclerView : RecyclerView? = null

    //list test
    private val mNicolasCageMovies = listOf(
        Movie("Raising Arizona", 1987),
        Movie("Vampire's Kiss", 1988),
        Movie("Con Air", 1997),
        Movie("Gone in 60 Seconds", 1997),
        Movie("National Treasure", 2004),
        Movie("The Wicker Man", 2006),
        Movie("Ghost Rider", 2007),
        Movie("Knowing", 2009)
    )


    /*private lateinit var recycler: RecyclerView*/

     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_horaire, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.HoraireRecyclerView) as RecyclerView



    }



}