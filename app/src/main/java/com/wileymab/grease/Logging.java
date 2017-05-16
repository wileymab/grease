package com.wileymab.grease;

import android.util.Log;

/**
 * Created by m441527 on 2/1/17.
 */

public class Logging {

    public static <T> String tag(Class<T> clazz) {
        return clazz.getSimpleName();
    }

    public static <T> String tag(Object object) {
        return tag(object.getClass());
    }



    public static void error(String tag, String formatString, Object... params) {
        Log.e(tag,String.format(formatString,params));
    }

    public static void error(String tag, String message) {
        Log.e(tag,message);
    }

    public static void error(String message) {
        Log.e("", message);
    }



    public static void debug(String tag, String formatString, Object... params) {
        Log.d(tag,String.format(formatString,params));
    }

    public static void debug(String tag, String message) {
        Log.d(tag,message);
    }

    public static void debug(String message) {
        Log.d("", message);
    }

}
