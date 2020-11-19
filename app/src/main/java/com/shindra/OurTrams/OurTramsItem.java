package com.shindra.OurTrams;

import android.text.Html;

import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;

public class OurTramsItem {

    // Attributes
    private int mImgTramLetter;
    private int mImgTram;
    private String mTramLineName;
    private ArrayList<Stop> mStop;


    // Constructors
    public OurTramsItem(String tramLineName, int imgTramLetter, int imgTram) {
        mImgTramLetter = imgTramLetter;
        mImgTram = imgTram;
        mTramLineName = tramLineName;
    }
    public OurTramsItem(String tramLineName, ArrayList<Stop> stop, int imgTramLetter, int imgTram) {
        mImgTramLetter = imgTramLetter;
        mImgTram = imgTram;
        mTramLineName = tramLineName;
        mStop = stop;
    }

    // Methods
    public int GetImgTram() {
        return mImgTram;
    }
    public int GetImgTramLetter() {
        return mImgTramLetter;
    }
    public String GetTramLineName(){return mTramLineName;}
    public void SetTramLineName(String tramLineName){mTramLineName=tramLineName;}
    public void SetImgTram(int imgResourceId){mImgTram = mImgTram;}
    public void SetImgTramLetter(int imgResourceId){mImgTramLetter = imgResourceId;}
    public void SetStops(ArrayList<Stop> stop){mStop=stop;}
}

