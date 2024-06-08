// Static Details class

package utils;

import java.awt.*;
import java.util.ArrayList;

public class SD {
    // Teams
    public static final String Nazi = "Nazi";
    public static final String Soviet = "Soviet";

    // Maps
    public static final String Map = "/maps/map.png";

    // Bases
        //Nazi
        public static final String WerwolfNaziBase = "Werwolf Nazi Base";
        public static final String KrasnohradNaziBase = "Secret Nazi Base in Krasnohrad";
        public static final String YaltaNaziBase = "Yalta \"DZIDZIO\" Nazi Base";

        // Soviets
        public static final String StalingradSovietBase = "Stalingrad Soviet Base";
        public static final String ElistaSovietBase = "Elista Soviet Base";
        public static final String KubanSovietBase = "Kuban Soviet Base";

        // Capture Points
        public static final String RostovCapturePoint = "Rostov Capture Point";
        public static final String RostovCapturePointImgFile = "/bases/point1.png";
        public static final String DonetskCapturePoint = "Donetsk Capture Point";
        public static final String DonetskCapturePointImgFile = "/bases/point2.png";
        public static final String MaikopCapturePoint = "Maikop Capture Point";
        public static final String MaikopCapturePointImgFile = "bases/point3.png";
    //

    // Types of Entities
    public static final String Infantry = "Infantry";
    public static final String SquadLeader = "Squad Leader";
    public static final String Kombat = "Kombat";

    public static ArrayList<String> getAllTeams() {
        ArrayList<String> teams = new ArrayList<>();
        teams.add(Nazi);
        teams.add(Soviet);
        return teams;
    }
    
    public static ArrayList<String> getAllNaziBasesNames() {
        ArrayList<String> names = new ArrayList<>();
        names.add(WerwolfNaziBase);
        names.add(KrasnohradNaziBase);
        names.add(YaltaNaziBase);
        return names;
    }

    public static ArrayList<String> getAllSovietBasesNames() {
        ArrayList<String> names = new ArrayList<>();
        names.add(StalingradSovietBase);
        names.add(ElistaSovietBase);
        names.add(KubanSovietBase);
        return names;
    }

    public static ArrayList<String> getAllCapturePointNames() {
        ArrayList<String> names = new ArrayList<>();
        names.add(RostovCapturePoint);
        names.add(DonetskCapturePoint);
        names.add(MaikopCapturePoint);
        return names;
    }

    public static ArrayList<String> getAllBasesNames() {
        ArrayList<String> names = new ArrayList<>();
        names.addAll(getAllNaziBasesNames());
        names.addAll(getAllSovietBasesNames());
        return names;
    }

    public static ArrayList<String> getAllBasesAndCapturePointsNames() {
        ArrayList<String> names = new ArrayList<>();
        names.addAll(getAllNaziBasesNames());
        names.addAll(getAllSovietBasesNames());
        names.addAll(getAllCapturePointNames());
        return names;
    }

    public static ArrayList<String> getAllEntityTypes() {
        ArrayList<String> names = new ArrayList<>();
        names.add(Infantry);
        names.add(SquadLeader);
        names.add(Kombat);
        return names;
    }

    public static ArrayList<String> getSortOptions() {
        ArrayList<String> sortOptions = new ArrayList<>();
        sortOptions.add("HP");
        sortOptions.add("ID");
        sortOptions.add("Damage");
        return sortOptions;
    }
}
