package com.cxc.logutil.util;

import android.util.Log;

/**
 * Created by cxc on 2017/4/23.
 */

public class LogUtil {

    public static final String LOG_TAG = "CXC";

    //正式发布应用时将此标志改为false即可关闭日志打印
    public static boolean DEBUG = true;

    public LogUtil(){
    }

    public static final void log(String logtext){
        if(DEBUG){
            Log.i(LOG_TAG,logtext);
        }
    }
}
