package com.shindra.Service;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Line;

import java.util.Calendar;
import java.util.Date;

public abstract class  Converter
{
    private static final String lineA = "Parc des Sports - Illkirch Graffenstaden";
    private static final String lineB = "Lingolsheim Tiergaertel - Hoenheim Gare";
    private static final String lineC = "Gare Centrale - Neuhof Rodolphe Reuss";
    private static final String lineD = "Poteries - Port du Rhin / Kehl Rathaus";
    private static final String lineE = "Robertsau l'Escale - Campus d'Illkirch";
    private static final String lineF = "Comtes - Place d'Islande";

    public static int lineNameToLineLetter(String lineName)
    {
        switch (lineName)
        {
            case lineA:
                return R.string.lineA;
            case lineB:
                return R.string.lineB;
            case lineC:
                return R.string.lineC;
            case lineD:
                return R.string.lineD;
            case lineE:
                return R.string.lineE;
            case lineF:
                return R.string.lineF;
            default:
                return R.string.unknownLine;
        }
    }

    public static int lineNameToLineIcon(String lineName)
    {
        switch (lineName)
        {
            case lineA:
                return R.drawable.tram_a;
            case lineB:
                return R.drawable.tram_b;
            case lineC:
                return R.drawable.tram_c;
            case lineD:
                return R.drawable.tram_d;
            case lineE:
                return R.drawable.tram_e;
            case lineF:
                return R.drawable.tram_f;
            default:
                return R.drawable.tram;
        }
    }

    public static int lineNameToLineColor(String lineName)
    {
        switch (lineName)
        {
            case lineA:
                return R.color.LineA;
            case lineB:
                return R.color.LineB;
            case lineC:
                return R.color.LineC;
            case lineD:
                return R.color.LineD;
            case lineE:
                return R.color.LineE;
            case lineF:
                return R.color.LineF;
            default:
                return R.color.black;
        }
    }

    public static String dateToTime(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY) + "h" + calendar.get(Calendar.MINUTE);
    }
}
