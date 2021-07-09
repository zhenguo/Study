package com.qihoo.reflection

import android.app.IActivityManager
import android.util.Log
import java.lang.Exception

private const val TAG = "ActivityManagerServiceO"

class ActivityManagerServiceOreo {
    companion object {
        fun get(): IActivityManager {
            val activityManagerClass = Class.forName("android.app.ActivityManager")
            val getServiceMethod = activityManagerClass.getDeclaredMethod("getService")
            return getServiceMethod.invoke(activityManagerClass) as IActivityManager
        }

        fun openContentUri(uriString: String) {
            try {
                val iActivityManagerClass = Class.forName("android.app.IActivityManager")
                val openContentUriMethod = iActivityManagerClass.getDeclaredMethod("openContentUri", String::class.java)
                openContentUriMethod.invoke(get(), uriString)
            } catch (e: Exception) {
                Log.e(TAG, "openContentUri: ", e)
            }
        }
    }
}