package com.shindra;

public class RecyclerTestAdapter {

    private int mImageResource;
    private String mText1;
    private String mText2;

    public RecyclerTestAdapter(int ImageRessource, String Text1, String Text2){
        mImageResource = ImageRessource;
        mText1 = Text1;
        mText2 = Text2;
    }

    public int getmImageResource() {
        return mImageResource;
    }
    public String getmText1(){
        return mText1;
    }

    public String getmText2(){
        return mText2;
    }
}
