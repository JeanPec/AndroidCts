package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.shindra.ctslibrary.bo.Stop;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Objects;


public class TimeRecyclerViewAdapter extends RecyclerView.Adapter<TimeRecyclerViewAdapter.TimeViewHolder> {

    private final ArrayList<Stop> mStops;
    private final String mLineName;

    public static class TimeViewHolder extends RecyclerView.ViewHolder {

        public TextView mTime;
        public TextView mTimeLine;
        public TextView mTimeStop;

        public TimeViewHolder(View pItemView) {

            super(pItemView);
            mTime = itemView.findViewById(R.id.Time);
            mTimeLine = itemView.findViewById(R.id.TimeLine);
            mTimeStop = itemView.findViewById(R.id.TimeStop);
        }
    }

    public TimeRecyclerViewAdapter(ArrayList<Stop> pStops, String pLineName) {

        this.mStops = pStops;
        this.mLineName = pLineName;
    }

    @NotNull
    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_cardview, parent, false);
        return new TimeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TimeViewHolder pHolder, int pPosition) {

        Stop mStop = mStops.get(pPosition);
        pHolder.mTimeStop.setText(mStop.getName());
        pHolder.mTimeLine.setText("Ligne" + " " + mLineName);

        switch (mLineName) {
            case "A":
                pHolder.mTimeLine.setTextColor(ContextCompat.getColor(pHolder.mTimeLine.getContext(), R.color.LineA));
                break;
            case "B":
                pHolder.mTimeLine.setTextColor(ContextCompat.getColor(pHolder.mTimeLine.getContext(), R.color.LineB));
                break;
            case "C":
                pHolder.mTimeLine.setTextColor(ContextCompat.getColor(pHolder.mTimeLine.getContext(), R.color.LineC));
                break;
            case "D":
                pHolder.mTimeLine.setTextColor(ContextCompat.getColor(pHolder.mTimeLine.getContext(), R.color.LineD));
                break;
            case "E":
                pHolder.mTimeLine.setTextColor(ContextCompat.getColor(pHolder.mTimeLine.getContext(), R.color.LineE));
                break;
            case "F":
                pHolder.mTimeLine.setTextColor(ContextCompat.getColor(pHolder.mTimeLine.getContext(), R.color.LineF));
                break;
            default:
                pHolder.mTimeLine.setTextColor(ContextCompat.getColor(pHolder.mTimeLine.getContext(), R.color.Body2));
                break;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH'h'mm");
        pHolder.mTime.setText(simpleDateFormat.format(Objects.requireNonNull(mStop.getEstimatedArrivalTime())));
    }

    @Override
    public int getItemCount() {

        return mStops.size();
    }
}
