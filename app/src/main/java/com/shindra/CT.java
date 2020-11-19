package com.shindra;

// Class used as a group of functions. CT = CorrespondanceTable
public abstract class CT {
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

    public static String GetLineRef(String tramName) {
        switch (tramName) {
            case "Parc des Sports - Illkirch Graffenstaden":
                return "A";
            case "Lingolsheim Tiergaertel - Hoenheim Gare":
                return "B";
            case "Gare Centrale - Neuhof Rodolphe Reuss":
                return "C";
            case "Poteries - Port du Rhin / Kehl Rathaus":
                return "D";
            case "Robertsau l'Escale - Campus d'Illkirch":
                return "E";
            case "Comtes - Place d'Islande":
                return "F";
            default:
                return "notExistingYet";
        }
    }
}
