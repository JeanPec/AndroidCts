package com.shindra.OurTrams;

public class OurTramsItem {

    // Attributes
    private int mImgTramLetter;
    private int mImgTram;

    // Constructor
    public OurTramsItem(int imgTramLetter, int imgTram) {
        mImgTram = imgTram;
        mImgTramLetter = imgTramLetter;
    }

    // Methods
    public int getmImgTram() {
        return mImgTram;
    }
    public int getmImgTramLetter() {
        return mImgTramLetter;
    }
    public void changeImgTramLetter(int image) {mImgTramLetter=image;}
}
