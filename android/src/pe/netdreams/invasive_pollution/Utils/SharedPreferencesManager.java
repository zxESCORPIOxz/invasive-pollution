package pe.netdreams.invasive_pollution.Utils;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static final String APP_SETTINGS_FILE = "INVASIVE_POLLUTION";

    private SharedPreferencesManager() {

    }

    public static void setStringValue(Context ctx, String key, String value) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setIntValue(Context ctx, String key, int value) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void setBooleanValue(Context ctx, String key, boolean value) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static String getStringValue(Context ctx, String key) {
        return ctx.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE).getString(key, null);
    }

    public static boolean getBooleanValue(Context ctx, String key) {
        return ctx.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE).getBoolean(key, false);
    }

    public static int getIntValue(Context ctx, String key) {
        return ctx.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE).getInt(key,0);
    }
    public static int getVolume(Context ctx, String key) {
        return ctx.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE).getInt(key,50);
    }
}

