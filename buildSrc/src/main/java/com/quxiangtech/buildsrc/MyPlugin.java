package com.quxiangtech.buildsrc;

import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.gradle.AppExtension;
import com.android.build.gradle.internal.pipeline.TransformManager;

import org.gradle.api.Action;
import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Set;

public class MyPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        LogUtil.println("apply====================================");
        AppExtension appExtension = project.getExtensions().findByType(AppExtension.class);
        if (appExtension != null) {
            LogUtil.println("registerTransform===================" + appExtension.getCompileSdkVersion());
            appExtension.registerTransform(new ARouterTransform(project));
            LogUtil.println("registerTransform===================end");
        }
//        project.afterEvaluate(new Action<Project>() {
//            @Override
//            public void execute(@NotNull Project project) {
//                if (!project.getPlugins().hasPlugin("com.android.application")) {
//                    throw new GradleException("Android Application plugin required");
//                }
//                LogUtil.println("afterEvaluate=========================");
//                AppExtension appExtension = project.getExtensions().findByType(AppExtension.class);
//                if (appExtension != null) {
//                    LogUtil.println("registerTransform===================" + appExtension.getCompileSdkVersion());
//                    appExtension.registerTransform(new ARouterTransform(project));
//                    LogUtil.println("registerTransform===================end");
//                }
//            }
//        });
    }
}