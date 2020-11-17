package com.shindra.Service;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Line;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class  Converter
{

    public static int lineLetterToLineIcon(String lineLetter)
    {
        switch (lineLetter)
        {
            case "A":
                return R.drawable.tram_a;
            case "B":
                return R.drawable.tram_b;
            case "C":
                return R.drawable.tram_c;
            case "D":
                return R.drawable.tram_d;
            case "E":
                return R.drawable.tram_e;
            case "F":
                return R.drawable.tram_f;
            default:
                return R.drawable.tram;
        }
    }

    public static int lineLetterToLineColor(String lineLetter)
    {
        switch (lineLetter)
        {
            case "A":
                return R.color.LineA;
            case "B":
                return R.color.LineB;
            case "C":
                return R.color.LineC;
            case "D":
                return R.color.LineD;
            case "E":
                return R.color.LineE;
            case "F":
                return R.color.LineF;
            default:
                return R.color.black;
        }
    }

    public static String dateToTime(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH'h'mm");
        return simpleDateFormat.format(date);
    }
}
