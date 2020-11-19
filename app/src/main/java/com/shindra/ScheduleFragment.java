package com.shindra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String lineName;
    RecyclerView recyclerScheduleView;
    ArrayList<myStop> myStops;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScheduleFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduleFragment newInstance(String param1 , String param2) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1 , param1);
        args.putString(ARG_PARAM2 , param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         lineName = this.getArguments().getString("lineName");
        View view = inflater.inflate(R.layout.schedule_frag, container , false);
        recyclerScheduleView = view.findViewById(R.id.recyclerViewScheduleFrag);
        recyclerScheduleView.setLayoutManager(new LinearLayoutManager(getContext()));
        myStops = new ArrayList<>();
        myStops.add(new myStop(lineName,"cdjfoj","18H"));
        myStops.add(new myStop("A","df","20H"));

        recyclerScheduleView.setAdapter(new RecyclerScheduleAdapter((myStops)));
        return view;
    }
}