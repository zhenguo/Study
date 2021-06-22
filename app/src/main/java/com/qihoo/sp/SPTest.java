package com.qihoo.sp;

import android.content.Context;
import android.content.SharedPreferences;

public class SPTest {
    private Context mAppContext;
    private static SharedPreferences sSP;
    private static final String SP_NAME = "sp_test";

    public SPTest(Context appContext) {
        this.mAppContext = appContext;

        /**
         * 创建sp_test.xml
         */
        sSP = mAppContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

        /**
         * edit会调用wait() 耗时
         * putXxxx() 会使用一个Map来保存数据
         *
         * 写入到文件的时候会使用CountDownLatch等待
         */
        sSP.edit().putBoolean(SP_NAME, true).apply();
        sSP.edit().putBoolean(SP_NAME, false).commit();

        /**
         * 为什么会有进程问题
         */
    }
}
