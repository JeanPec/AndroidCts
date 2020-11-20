package com.shindra.Activites;


import java.util.Date;

public class TrameStop {

        String nomArret;
        String nomLine;
        Date horaire;

        public TrameStop(String stop, String nomLine1, Date horaire1){
                nomArret = stop;
                nomLine = nomLine1;
                horaire = horaire1;
        }


        public String getNameStop() { return nomArret; }
        public  String  getNameLine() {return nomLine;}
        public Date getHoraire() {return horaire;}

}
