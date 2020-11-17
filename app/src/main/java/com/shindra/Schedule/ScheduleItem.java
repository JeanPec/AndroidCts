package com.shindra.Schedule;

public class ScheduleItem {

    // Attributes
    private String mTextLocation;
    private String mTextNextDepartureTime;
    private String mTextTramLine;

    // Constructor
    public ScheduleItem(String textLocation, String textNextDepartureTime, String textTramLine) {
        mTextLocation = textLocation;
        mTextNextDepartureTime = textNextDepartureTime;
        mTextTramLine = textTramLine;
    }

    // Methods
    public String getmTextLocation() { return mTextLocation; }
    public String getmTextNextDepartureTime() { return mTextNextDepartureTime; }
    public String getmTextTramLine() { return mTextTramLine; }
}
