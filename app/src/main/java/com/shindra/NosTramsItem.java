//Ajout 18/11/2020
package com.shindra;

public class NosTramsItem {

    private int imageTram;
    private String text1;
    private String text2;

    //Constructor
    public NosTramsItem(int imageTram, String text1, String text2){
        this.imageTram = imageTram;
        this.text1 = text1;
        this.text2 = text2;
    }

    public int getImageTram(){
        return imageTram;
    }
    public String getText1(){
        return text1;
    }
    public String getText2(){
        return text2;
    }


}
