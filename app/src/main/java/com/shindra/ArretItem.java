package com.shindra;

public class ArretItem {
    private String mTextBodyLine;
    private String mTextBody2;
    private String mTextHeadline1;
    private String mTextTime;

    public ArretItem(String textBodyLine, String textBody2, String textHeadline1, String textTime) {
        mTextBodyLine = textBodyLine;
        mTextBody2 = textBody2;
        mTextHeadline1 = textHeadline1;
        mTextTime = textTime;
    }

    public String getmTextBodyLine() {return mTextBodyLine;}
    public String getmTextBody2() {return mTextBody2;}
    public String getmTextHeadline1() {return mTextHeadline1;}
    public String getmTextTime() {return mTextTime;}
}
