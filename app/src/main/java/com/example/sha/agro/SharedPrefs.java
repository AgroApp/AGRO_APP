package com.example.sha.agro;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sha on 06-06-2019.
 */

public class SharedPrefs {

    final static String FileName = "AGRO2";

    public static String readSharedSetting(Context ctx,String settingName, String defaultValue){
        SharedPreferences sharedPref = ctx.getSharedPreferences(FileName,Context.MODE_PRIVATE);
        return sharedPref.getString(settingName,defaultValue);
    }

    public static void saveSharedSetting(Context ctx,String settingName, String settingValue){
        SharedPreferences sharedPref = ctx.getSharedPreferences(FileName,Context.MODE_PRIVATE);
        SharedPreferences .Editor editor = sharedPref.edit();
        editor.putString(settingName,settingValue);
        editor.apply();
    }
}
