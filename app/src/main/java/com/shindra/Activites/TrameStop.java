package com.shindra.Activites;


import java.util.Date;

public class TrameStop {

        String nomArrêt;
        String nomLine;
        Date horaire;

        public TrameStop(String stop, String nomLine1, Date horaire1){
                nomArrêt = stop;
                nomLine = nomLine1;
                horaire = horaire1;
        }


        public String getNameStop() { return nomArrêt; }
        public  String  getNameLine() {return nomLine;}
        public Date getHoraire() {return horaire;}

}
