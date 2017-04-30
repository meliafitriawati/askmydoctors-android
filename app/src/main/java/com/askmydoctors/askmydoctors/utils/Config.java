package com.askmydoctors.askmydoctors.utils;

import android.content.SharedPreferences;
import android.app.Activity;
import android.content.Context;

public class Config {
    public static final String SP_CONFIG = "AMD";
    static SharedPreferences settings;
    static SharedPreferences.Editor editor;
    public static final String URL = "http://192.168.1.15/askmydoctors/api/";
    public static final String URL_WEB = "http://192.168.1.15/askmydoctors/";

    public static void SetString(Activity act, String key, String value){
        settings = act.getSharedPreferences(SP_CONFIG, Context.MODE_PRIVATE);;
        editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String GetString(Activity act, String key){
        settings = act.getSharedPreferences(SP_CONFIG, 0);
        String val = settings.getString(key, "");
        return val;
    }

}
