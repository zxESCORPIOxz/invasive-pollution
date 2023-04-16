package pe.netdreams.invasive_pollution.Utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import pe.netdreams.invasive_pollution.Model.Ammo;
import pe.netdreams.invasive_pollution.Model.Gun;
import pe.netdreams.invasive_pollution.Model.Nave;
import pe.netdreams.invasive_pollution.R;

public class MiDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String TBL_NAVE = "tblNave";
    public static final String TBL_AMMO = "tblAmmo";
    public static final String TBL_GUN = "tblGun";
    private static final String DATABASE_NAME = "DBINVASIVE.db";

    public MiDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tblNave (" +
                "id INTEGER PRIMARY KEY, " +
                "nombre TEXT," +
                "recurso INTEGER," +
                "blindaje INTEGER," +
                "vida INTEGER," +
                "velocidad FLOAT," +
                "precio INTEGER," +
                "estado INTEGER" +
                ")");

        db.execSQL("CREATE TABLE tblAmmo (" +
                "id INTEGER PRIMARY KEY, " +
                "nombre TEXT," +
                "recurso INTEGER," +
                "damage INTEGER," +
                "precio INTEGER," +
                "estado INTEGER" +
                ")");

        db.execSQL("CREATE TABLE tblGun (" +
                "id INTEGER PRIMARY KEY, " +
                "nombre TEXT," +
                "recurso INTEGER," +
                "damage INTEGER," +
                "precio INTEGER," +
                "estado INTEGER" +
                ")");

        initDATA(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS miTabla");
        onCreate(db);
    }

    public void initDATA(SQLiteDatabase db){
        db.execSQL("INSERT INTO tblNave VALUES (0, 'H2151-1', " + R.drawable.nave1 + ", 1000, 500, 120, 2000,0)");
        db.execSQL("INSERT INTO tblNave VALUES (1, 'H2SAD-2', " + R.drawable.nave2 + ", 2000, 550, 130, 2500,1)");
        db.execSQL("INSERT INTO tblNave VALUES (2, 'JTRY5-3', " + R.drawable.nave3 + ", 3000, 600, 140, 3000,1)");
        db.execSQL("INSERT INTO tblNave VALUES (3, 'JSZEN-4', " + R.drawable.nave4 + ", 4000, 650, 150, 3500,1)");
        db.execSQL("INSERT INTO tblNave VALUES (4, 'ZVAVV-5', " + R.drawable.nave5 + ", 5000, 700, 160, 4000,1)");

        db.execSQL("INSERT INTO tblAmmo VALUES (0, 'Clasic', " + R.drawable.ic_ammo_1 + ", 10, 100000,0)");
        db.execSQL("INSERT INTO tblAmmo VALUES (1, 'Blindada', " + R.drawable.ic_ammo_2 + ", 12, 100000,1)");
        db.execSQL("INSERT INTO tblAmmo VALUES (2, 'Perforante'," + R.drawable.ic_ammo_3 + ", 14, 100000,1)");
        db.execSQL("INSERT INTO tblAmmo VALUES (3, 'Atomica'," + R.drawable.ic_ammo_4 + ", 16, 100000,1)");
        db.execSQL("INSERT INTO tblAmmo VALUES (4, 'Lazer',"+ R.drawable.ic_ammo_5 + ", 18, 100000,1)");

        db.execSQL("INSERT INTO tblGun VALUES (0, 'GUN 1'," + R.drawable.ic_gun_1 + ", 5, 10000,0)");
        db.execSQL("INSERT INTO tblGun VALUES (1, 'GUN 2'," + R.drawable.ic_gun_2 + ", 6, 10000,1)");
        db.execSQL("INSERT INTO tblGun VALUES (2, 'GUN 3'," + R.drawable.ic_gun_3 + ", 7, 10000,1)");
        db.execSQL("INSERT INTO tblGun VALUES (3, 'GUN 4'," + R.drawable.ic_gun_4 + ", 8, 10000,1)");
        db.execSQL("INSERT INTO tblGun VALUES (4, 'GUN 5'," + R.drawable.ic_gun_5 + ", 9, 10000,1)");
    }

    public ArrayList<Nave> getNaves() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Nave> navesRegistradas = new ArrayList<>();
        String[] columns = {
                "id",
                "nombre",
                "recurso",
                "blindaje",
                "vida",
                "velocidad",
                "precio",
                "estado"
        };
        Cursor cursor = db.query(TBL_NAVE, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                int recurso = cursor.getInt(2);
                int blindaje = cursor.getInt(3);
                int vida = cursor.getInt(4);
                int cadencia = cursor.getInt(5);
                int precio = cursor.getInt(6);
                int estado = cursor.getInt(7);

                Nave nave = new Nave(id, nombre, recurso, blindaje, vida, cadencia, precio, estado);
                navesRegistradas.add(nave);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return navesRegistradas;
    }

    public ArrayList<Ammo> getAmmos() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Ammo> listaAmmo = new ArrayList<>();

        String[] columnas = {"id", "nombre", "recurso", "damage", "precio", "estado"};
        Cursor cursor = db.query(TBL_AMMO, columnas, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            int recurso = cursor.getInt(2);
            int damage = cursor.getInt(3);
            int precio = cursor.getInt(4);
            int estado = cursor.getInt(5);

            listaAmmo.add(new Ammo(id, nombre, recurso, damage, precio, estado));
        }

        cursor.close();
        return listaAmmo;
    }
    public ArrayList<Gun> getGuns() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Gun> listaAmmo = new ArrayList<>();

        String[] columnas = {"id", "nombre", "recurso", "damage", "precio", "estado"};
        Cursor cursor = db.query(TBL_GUN, columnas, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            int recurso = cursor.getInt(2);
            int damage = cursor.getInt(3);
            int precio = cursor.getInt(4);
            int estado = cursor.getInt(5);

            listaAmmo.add(new Gun(id, nombre, recurso, damage, precio, estado));
        }

        cursor.close();
        return listaAmmo;
    }

    public Nave getNaveById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TBL_NAVE,
                new String[]{"id", "nombre", "recurso", "blindaje", "vida", "velocidad", "precio", "estado"},
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        if (cursor != null)
            cursor.moveToFirst();

        Nave nave = new Nave(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getInt(3),
                cursor.getInt(4),
                cursor.getInt(5),
                cursor.getInt(6),
                cursor.getInt(7)
        );

        cursor.close();
        return nave;
    }

    public Ammo getAmmoById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TBL_AMMO,
                new String[]{"id", "nombre", "recurso", "damage", "precio", "estado"},
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        if (cursor != null)
            cursor.moveToFirst();

        Ammo ammo = new Ammo(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getInt(3),
                cursor.getInt(4),
                cursor.getInt(5)
        );

        cursor.close();
        return ammo;
    }

    public Gun getGunById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TBL_GUN,
                new String[]{"id", "nombre", "recurso", "damage", "precio", "estado"},
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        if (cursor != null)
            cursor.moveToFirst();

        Gun gun = new Gun(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getInt(3),
                cursor.getInt(4),
                cursor.getInt(5)
        );

        cursor.close();
        return gun;
    }
}