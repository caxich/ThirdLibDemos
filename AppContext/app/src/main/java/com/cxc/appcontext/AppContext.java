package com.cxc.appcontext;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cxc on 2017/4/23.
 */

public class AppContext extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        mContext = getApplicationContext();
    }

    /**
    * 获取全局context，用于Toast、SharedPreferences操作
    * @param 无
    * @return ApplicationContext，全局context
    */
    public static Context getContext(){
        return mContext;
    }
}
