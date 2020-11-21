package com.shindra;

// Class used as a group of functions
public class HelperLine {
    public static int GetTramLetterImg(String tramLetter) {
        switch (tramLetter) {
            case "A":
                return R.drawable.tram_a;
            case "B":
                return R.drawable.tram_b;
            case "C":
                return R.drawable.tram_c;
            case "D":
                return R.drawable.tram_d;
            case "E":
                return R.drawable.tram_e;
            case "F":
                return R.drawable.tram_f;
            default:
                return R.drawable.ic_launcher_background;
        }
    }

    public static int GetLineColor(String tramLetter) {
        switch (tramLetter) {
            case "A":
                return R.color.LigneA;
            case "B":
                return R.color.LigneB;
            case "C":
                return R.color.LigneC;
            case "D":
                return R.color.LigneD;
            case "E":
                return R.color.LigneE;
            case "F":
                return R.color.LigneF;
            default:
                return R.color.Headline;
        }
    }
}
