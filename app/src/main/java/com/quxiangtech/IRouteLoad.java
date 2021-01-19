package com.quxiangtech;

import android.app.Activity;

import java.util.Map;

public interface IRouteLoad {
    void loadInto(Map<String, Class<? extends Activity>> routers);
}
