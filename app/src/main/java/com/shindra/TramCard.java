package com.shindra;

import android.graphics.drawable.Drawable;
import android.widget.Button;

public class TramCard {

    private Drawable imgNomLigne;
    private Drawable imgTram;



    public TramCard(Drawable imgNomLigne, Drawable imgTram) {
        this.imgNomLigne = imgNomLigne;
        this.imgTram = imgTram;
    }

    public Drawable getImgTram() {
        return imgTram;
    }

    public Drawable getImgNomLigne() {
        return imgNomLigne;
    }


}
