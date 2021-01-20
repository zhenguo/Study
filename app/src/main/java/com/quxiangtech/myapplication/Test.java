package com.quxiangtech.myapplication;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public final Map<String, String> mRoutes = new HashMap<>();
    public static boolean routersRegistered = false;

    public void registerRouter() {
        ModuleRouteRegister register = new ModuleRouteRegister();
        register.register(mRoutes);
        routersRegistered = true;
    }

    static class ModuleRouteRegister implements RouteRegister {

        @Override
        public void register(Map<String, String> routes) {

        }
    }

    public interface RouteRegister {
        void register(Map<String, String> routes);
    }
}
