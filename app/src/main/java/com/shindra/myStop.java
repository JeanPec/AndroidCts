package com.shindra;

public class myStop {

    private String lineName;
    private String lineStation;
    private String expectedHour;


    public myStop(String lineName, String lineStation , String expectedHour) {
        this.lineName = lineName;
        this.lineStation = lineStation;
        this.expectedHour = expectedHour;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }



    public String getHours() {
        return this.expectedHour;
    }

    public void setExpectedHour(String expectedHour) {
        this.expectedHour = expectedHour;
    }

    public String getLineStation() {
        return lineStation;
    }

    public void setLineStation(String lineStation) {
        this.lineStation = lineStation;
    }


}
