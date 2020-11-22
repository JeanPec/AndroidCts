package com.shindra.Schedule

class ScheduleItem(private val mNextDepartureTime: String, private val mTramStationName: String,  private val mTramLineLetter: String) {

    // Methods
    fun getNextDepartureTime(): String { return mNextDepartureTime }
    fun getTramStationName(): String { return mTramStationName }
    fun getTramLineLetter(): String { return mTramLineLetter }
}