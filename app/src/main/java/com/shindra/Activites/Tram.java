package com.shindra.Activites;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

public class Tram {

    String nom_image;
    int drawable_id_svg;
    /*ImageView image;*/

    Tram(String tramName, int image){
        nom_image = tramName;
        drawable_id_svg = image;
    }

}
