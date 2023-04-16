package pe.netdreams.invasive_pollution.Utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Constans {

    public static List<Idioma> getListaIdioma(Context ctx) {
        List<Idioma> list = new ArrayList<>();

        Idioma espanol = new Idioma();
        espanol.setName("Español");

        Idioma ingles = new Idioma();
        ingles.setName("Ingles");

        Idioma italiano = new Idioma();
        italiano.setName("Etc");

        list.add(espanol);
        list.add(ingles);
        list.add(italiano);
        return list;
    }

    public static final int DESBLOQUEADO = 0;
    public static final int BLOQUEADO = 1;
    public static final String ID_USER = "ID_USER";
    public static final String NOMBRE_USER = "NOMBRE_USER";
    public static final String NAVE_SET = "NAVE_SET";
    public static final String GUN_SET = "GUN_SET";
    public static final String AMMO_SET = "AMMO_SET";
    public static final String TOTAL_COINS = "TOTAL_COINS";
    public static final String BEST_SCORE = "BEST_SCORE";
    public static final String BEST_LEVEL = "BEST_LEVEL";
    public static final String VOLUME_MUSIC = "VOLUME_MUSIC";
    public static final String VOLUME_SOUND = "VOLUME_SOUND";
}
