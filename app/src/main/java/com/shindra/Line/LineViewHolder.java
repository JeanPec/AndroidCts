package com.shindra.Line;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Line;

public class LineViewHolder extends RecyclerView.ViewHolder
{
    public ImageView tramIcon;
    public ImageView tramPicture;
    public Button scheduleButton;

    public LineViewHolder(@NonNull View itemView)
    {
        super(itemView);
        tramIcon = itemView.findViewById(R.id.tramIcon);
        tramPicture = itemView.findViewById(R.id.tramPicture);
        scheduleButton = itemView.findViewById(R.id.scheduleButton);
    }

    public void OnBind(Line line, ILineClickable callback)
    {
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                callback.OnLineClick(line);
            }
        });
    }
}
