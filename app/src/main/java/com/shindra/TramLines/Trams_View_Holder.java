package com.shindra.TramLines;

import android.widget.Button;
import android.widget.ImageView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Line;

public class Trams_View_Holder extends RecyclerView.ViewHolder
{
    private final ImageView tram_logo;
    private final Button button;

    interface ScheduleButtonClick
    {
        void onScheduleButtonClick(Line line);
    }
    public Trams_View_Holder(@NonNull View itemView)
    {
        super(itemView);
        this.tram_logo = itemView.findViewById(R.id.Tram_Logo);
        this.button = itemView.findViewById(R.id.Tram_view_button);
    }

    public void onBind(Line image, ScheduleButtonClick callBack)
    {
        switch (image.getName())
        {
            case "A":
                tram_logo.setImageResource(R.drawable.tram_a);
                break;

            case "B":
                tram_logo.setImageResource(R.drawable.tram_b);
                break;

            case "C":
                tram_logo.setImageResource(R.drawable.tram_c);
                break;

            case "D":
                tram_logo.setImageResource(R.drawable.tram_d);
                break;

            case "E":
                tram_logo.setImageResource(R.drawable.tram_e);
                break;

            case "F":
                tram_logo.setImageResource(R.drawable.tram_f);
                break;

            default:
                tram_logo.setImageResource(R.drawable.tram);
                break;
        }
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                callBack.onScheduleButtonClick(image);
            }
        });

    }
}

