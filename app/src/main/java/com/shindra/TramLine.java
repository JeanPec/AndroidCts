package com.shindra;

public class TramLine {

    private String nameLine;
    private int icon;
    private int tramImg;
    private String departureStation;
    private String arrivalStation;

    public TramLine(String nameLine1, int icon1, int tramImg1)
    {
        nameLine = nameLine1;
        icon = icon1;
        tramImg = tramImg1;
    }

    @Override
    public String toString() {
        return "TramLine{" +
                "nameLine=" + nameLine +
                ", departure Station=" + departureStation +
                ", arrival Station=" + arrivalStation +
                '}';
    }

    public String getNameLine() {
        return nameLine;
    }

    public void setNameLine(String nameLine) {
        this.nameLine = nameLine;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getTramImg() {
        return tramImg;
    }

    public void setTramImg(int tramImg) {
        this.tramImg = tramImg;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }
}
