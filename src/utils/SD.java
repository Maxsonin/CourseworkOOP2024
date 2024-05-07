package utils;

import java.util.ArrayList;

public class SD {
    public static final String WerwolfNaziBase = "Werwolf Nazi Base";
    public static final String KrasnohradNaziBase = "Secret Nazi Base in Krasnohrad";
    public static final String YaltaNaziBase = "Yalta \"DZIDZIO\" Nazi Base";

    public static final String Infantry = "Infantry";
    public static final String SquadLeader = "Squad Leader";
    public static final String Kombat = "Kombat";

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
