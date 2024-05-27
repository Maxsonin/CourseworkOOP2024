// Static Details class

package utils;

import java.util.ArrayList;

public class SD {
    // Teams
    public static final String Nazi = "Nazi";
    public static final String Soviet = "Soviet";

    // Bases
    public static final String WerwolfNaziBase = "Werwolf Nazi Base";
    public static final String KrasnohradNaziBase = "Secret Nazi Base in Krasnohrad";
    public static final String YaltaNaziBase = "Yalta \"DZIDZIO\" Nazi Base";

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

    public static ArrayList<String> getAllEntityTypes() {
        ArrayList<String> names = new ArrayList<>();
        names.add(Infantry);
        names.add(SquadLeader);
        names.add(Kombat);
        return names;
    }
}
