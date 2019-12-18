package com.anuj.pocjava.log;

import android.util.Log;

public class Logger {

    private static final String TAG = "Loger";

    public static void debugLog(String message) {
        Log.d(TAG, message);
    }
}
