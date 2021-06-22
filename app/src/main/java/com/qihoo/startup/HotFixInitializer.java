package com.qihoo.startup;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import com.qihoo.hotfix.HotFix;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

public class HotFixInitializer implements Initializer<HotFix> {
    @NonNull
    @Override
    public HotFix create(@NonNull Context context) {
        HotFix hotFix = HotFix.getInstance();
        try {
            hotFix.init(context, context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/patch.dex");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return hotFix;
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }
}
