package com.stxr.smartinfusion.utils;

import android.util.Log;

/*
 *  项目名：  MyApplication
 *  包名：    com.stxr.smartinfusion.utils
 *  文件名:   L
 *  创建者:   Stxr
 *  创建时间:  2017/5/13 11:31
 *  描述：    日记
 */
public class L {
    private static final boolean DEBUG = true;
    private static final String TAG = "smart_infusion";

    public static void i(String string) {
        Log.i(TAG, string);
    }
    public static void w(String string) {
        Log.w(TAG, string);
    }
    public static void d(String string) {
        Log.d(TAG, string);
    }
    public static void e(String string) {
        Log.e(TAG, string);
    }
    public static void wtf(String string) {
        Log.wtf(TAG, string);
    }
}
