package com.shindra.Schedule;

public class ScheduleItem {

    // Attributes
    private String mNextDepartureTime;
    private String mTramStationName;
    private String mTramLineLetter;

    // Constructor
    public ScheduleItem(String tramLineLetter, String tramStationName, String nextDepartureTime) {
        mNextDepartureTime = nextDepartureTime;
        mTramStationName = tramStationName;
        mTramLineLetter = tramLineLetter;
    }

    // Methods
    public String GetNextDepartureTime() { return mNextDepartureTime; }
    public String GetTramStationName() { return mTramStationName; }
    public String GetTramLineLetter() {return mTramLineLetter;}
}
