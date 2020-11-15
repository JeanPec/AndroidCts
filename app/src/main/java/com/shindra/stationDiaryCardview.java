package com.shindra;

public class stationDiaryCardview {

    private String _stationName;
    private String _lineName;
    private String _scheduledTime;


    public stationDiaryCardview(String stationName, String lineName, String scheduledTime)
    {
        _stationName = stationName;
        _lineName = lineName;
        _scheduledTime = scheduledTime;
    }

    public String getStationName()
    {

        return _stationName;
    }

    public String getLineName()
    {
        return _lineName;
    }

    public String getScheduledTime()
    {
        return _scheduledTime;
    }
}
