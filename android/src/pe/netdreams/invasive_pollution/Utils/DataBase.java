package pe.netdreams.invasive_pollution.Utils;

import android.content.Context;

import java.util.ArrayList;

import pe.netdreams.invasive_pollution.Model.Ammo;
import pe.netdreams.invasive_pollution.Model.Gun;
import pe.netdreams.invasive_pollution.Model.Nave;

public class DataBase {

    public static ArrayList<Nave> getNaves(Context ctx){
        MiDBHelper dbHelper = new MiDBHelper(ctx);
        return dbHelper.getNaves();
    }

    public static ArrayList<Ammo> getAmmos(Context ctx){
        MiDBHelper dbHelper = new MiDBHelper(ctx);
        return dbHelper.getAmmos();
    }

    public static ArrayList<Gun> getGuns(Context ctx){
        MiDBHelper dbHelper = new MiDBHelper(ctx);
        return dbHelper.getGuns();
    }

    public static Nave getNaveById(Context ctx,int id){
        MiDBHelper dbHelper = new MiDBHelper(ctx);
        return dbHelper.getNaveById(id);
    }

    public static Ammo getAmmoById(Context ctx,int id){
        MiDBHelper dbHelper = new MiDBHelper(ctx);
        return dbHelper.getAmmoById(id);
    }

    public static Gun getGunById(Context ctx,int id){
        MiDBHelper dbHelper = new MiDBHelper(ctx);
        return dbHelper.getGunById(id);
    }
}
