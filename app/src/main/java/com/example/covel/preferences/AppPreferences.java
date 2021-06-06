package com.example.covel.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppPreferences {
    private static SharedPreferences mPrefs;
    private static SharedPreferences.Editor mPrefsEditor;

    public static void login(Context ctx, int id, String userId, String password, String nickname,
                             String name, String rgnumber1, String rgnumber2) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putBoolean("is_logged_in", true);
        mPrefsEditor.putInt("id", id);
        mPrefsEditor.putString("userId", userId);
        mPrefsEditor.putString("password", password);
        mPrefsEditor.putString("nickname", nickname);
        mPrefsEditor.putString("name", name);
        mPrefsEditor.putString("rgnumber1", rgnumber1);
        mPrefsEditor.putString("rgnumber2", rgnumber2);
        mPrefsEditor.apply();
        mPrefsEditor.commit();
    }

    public static void logout() {
        mPrefsEditor.remove("id_logged_in");
        mPrefsEditor.remove("id");
        mPrefsEditor.remove("userId");
        mPrefsEditor.remove("password");
        mPrefsEditor.remove("nickname");
        mPrefsEditor.remove("name");
        mPrefsEditor.remove("rgnumber1");
        mPrefsEditor.remove("rgnumber2");
        mPrefsEditor.clear();
        mPrefsEditor.apply();
        mPrefsEditor.commit();
    }

    // login -> id_logged_in : true, logout -> id_logged_in : false
    public static boolean isUserLoggedIn(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getBoolean("is_logged_in", false);
    }

    public static int getId(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getInt("id", 0);
    }

    public static String getUserId(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getString("userId", "");
    }

    public static String getPassword(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getString("password", "");
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
        mPrefsEditor.commit();
    }

    public static String getName(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getString("name", "");
    }

    public static String getRgnumber1(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getString("rgnumber1", "");
    }

    public static String getRgnumber2(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getString("rgnumber2", "");
    }

}
