package com.example.covel.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppPreferences {
    private static SharedPreferences mPrefs;
    private static SharedPreferences.Editor mPrefsEditor;

    public static void remove() {
        mPrefsEditor.clear();
    }

    // login -> id_logged_in : true, logout -> id_logged_in : false
    public static boolean isUserLoggedIn(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getBoolean("id_logged_in", false);
    }
    public static void setUserLoggedIn(Context ctx, Boolean value) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putBoolean("id_logged_in", value);
        mPrefsEditor.apply();
    }

    public static int getId(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getInt("id", 0);
    }
    public static void setId(Context ctx, int value) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putInt("id", value);
        mPrefsEditor.apply();
    }

    public static String getUserId(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getString("userId", "");
    }
    public static void setUserId(Context ctx, String value) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putString("userId", value);
        mPrefsEditor.apply();
    }

    public static String getPassword(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getString("password", "");
    }
    public static void setPassword(Context ctx, String value) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putString("password", value);
        mPrefsEditor.apply();
    }

    public static String getNickname(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getString("nickname", "");
    }
    public static void setNickname(Context ctx, String value) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putString("nickname", value);
        mPrefsEditor.apply();
    }

    public static String getName(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getString("name", "");
    }
    public static void setName(Context ctx, String value) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putString("name", value);
        mPrefsEditor.apply();
    }

    public static String getRgnumber1(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getString("rgnumber1", "");
    }
    public static void setRgnumber1(Context ctx, String value) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putString("rgnumber1", value);
        mPrefsEditor.apply();
    }

    public static String getRgnumber2(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getString("rgnumber2", "");
    }
    public static void setRgnumber2(Context ctx, String value) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putString("rgnumber2", value);
        mPrefsEditor.apply();
    }

}
