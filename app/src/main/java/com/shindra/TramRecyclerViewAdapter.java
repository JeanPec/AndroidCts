package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import com.shindra.ctslibrary.bo.Line;
import org.jetbrains.annotations.NotNull;


public class TramRecyclerViewAdapter extends RecyclerView.Adapter<TramViewHolder> {

    private final ArrayList<Line> mTramLines;
    private final RecyclerItemClick mCallback;

    public TramRecyclerViewAdapter(ArrayList<Line> pTramLines, RecyclerItemClick pCallback) {

        mTramLines = pTramLines;
        this.mCallback = pCallback;
    }

    @NotNull
    @Override
    public TramViewHolder onCreateViewHolder(ViewGroup parent, int pViewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tram_cardview, parent, false);
        return new TramViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NotNull TramViewHolder pHolder, int pPosition) {

        Line mLine = mTramLines.get(pPosition);
        switch (mLine.getName())
        {
            case ("A"):
            {
                pHolder.mImageView.setImageResource(R.drawable.tram_a);
            }
            break;
            case ("B"):
            {
                pHolder.mImageView.setImageResource(R.drawable.tram_b);
            }
            break;
            case ("C"):
            {
                pHolder.mImageView.setImageResource(R.drawable.tram_c);
            }
            break;
            case ("D"):
            {
                pHolder.mImageView.setImageResource(R.drawable.tram_d);
            }
            break;
            case ("E"):
            {
                pHolder.mImageView.setImageResource(R.drawable.tram_e);
            }
            break;
            case ("F"):
            {
                pHolder.mImageView.setImageResource(R.drawable.tram_f);
            }
            break;

            default:
                pHolder.mImageView.setImageResource(R.drawable.tram);
                break;
        }
        pHolder.onBind(mLine, mCallback);
    }

    @Override
    public int getItemCount() {

        return mTramLines.size();
    }
}
