package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import com.shindra.ctslibrary.bo.Line;


public class TramRecyclerViewAdapter extends RecyclerView.Adapter<TramRecyclerViewAdapter.ExampleViewHolder> {

    private ArrayList<Line> mTramLines;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;

        public ExampleViewHolder(View itemView) {

            super(itemView);
            mImageView = itemView.findViewById(R.id.TramText);
        }
    }

    public TramRecyclerViewAdapter(ArrayList<Line> pTramLines) {

        mTramLines = pTramLines;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int pViewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tram_cardview, parent, false);
        return new ExampleViewHolder(mView);

    }

    @Override
    public void onBindViewHolder(ExampleViewHolder pHolder, int position) {

        Line mLine = mTramLines.get(position);
        switch (mLine.getName())
        {
            case ("Tram A"):
            {
                pHolder.mImageView.setImageResource(R.drawable.tram_a);
            }
            break;
            case ("Tram B"):
            {
                pHolder.mImageView.setImageResource(R.drawable.tram_b);
            }
            break;
            case ("Tram C"):
            {
                pHolder.mImageView.setImageResource(R.drawable.tram_c);
            }
            break;
            case ("Tram D"):
            {
                pHolder.mImageView.setImageResource(R.drawable.tram_d);
            }
            break;
            case ("Tram E"):
            {
                pHolder.mImageView.setImageResource(R.drawable.tram_e);
            }
            break;
            case ("Tram F"):
            {
                pHolder.mImageView.setImageResource(R.drawable.tram_f);
            }
            break;

            default:
                pHolder.mImageView.setImageResource(R.drawable.tram);
                break;
        }
    }
    @Override
    public int getItemCount() {

        return mTramLines.size();
    }
}
