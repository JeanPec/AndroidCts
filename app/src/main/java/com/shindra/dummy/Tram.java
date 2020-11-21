package com.shindra.dummy;

import android.graphics.drawable.Drawable;
import android.widget.Switch;

import com.shindra.R;
import com.shindra.ctslibrary.apibo.Lines;
import com.shindra.ctslibrary.bo.Line;

public class Tram {
    public final String nom;
    public int image;

    public Tram(String nom, int image) {
        this.nom = nom;
        this.image = image;
    }

    public Tram(Line data) {
        this.nom = data.getName();
        switch (data.getName()){
            case "A":
                this.image = R.drawable.tram_a;
                break;
            case "B":
                this.image = R.drawable.tram_b;
                break;
            case "C":
                this.image = R.drawable.tram_c;
                break;
            case "D":
                this.image = R.drawable.tram_d;
                break;
            case "E":
                this.image = R.drawable.tram_e;
                break;
            case "F":
                this.image = R.drawable.tram_f;
                break;
            default:
                this.image = R.drawable.tram;
                break;
        }
    }
}
