package com.shindra.Line;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.Stop.StopActivity;
import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class LineFragment extends Fragment
{
    private RecyclerView lines;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_line, container, false);

        lines = view.findViewById(R.id.lines);
        lines.setLayoutManager(new LinearLayoutManager(getContext()));
        lines.setAdapter(new LineAdapter(new ArrayList<Line>(), new ILineClickable() {
            @Override
            public void OnLineClick(Line line)
            {
                //Launch the stop activity here
                Intent intent = new Intent(getActivity(), StopActivity.class);
                intent.putExtra("lineName", line.getName());
                startActivity(intent);
            }
        }));

        return view;
    }

    public void updateWidgets(ArrayList<Line> tramLines)
    {
        ((LineAdapter) lines.getAdapter()).setLines(tramLines);
        lines.getAdapter().notifyDataSetChanged();
    }
}
