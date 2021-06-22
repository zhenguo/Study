package com.qihoo.zroutercompiler;

import com.qihoo.annotation.Route;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;


@SupportedAnnotationTypes("com.quxiangtech.annotation.Route")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ZRouterCompiler extends AbstractProcessor {
    private Filer filer;
    private Messager messager;
    private Elements elements;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
        elements = processingEnvironment.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Collection<? extends Element> annotationElements = roundEnvironment.getElementsAnnotatedWith(Route.class);
        List<TypeElement> types = ElementFilter.typesIn(annotationElements);
        String packageName = null;
        String[] names = null;
        List<String> classNames = new ArrayList<>();

        for (TypeElement type : types) {
            PackageElement packageElement = (PackageElement) type.getEnclosingElement();
            packageName = packageElement.getQualifiedName().toString();
            names = type.getAnnotation(Route.class).value();
            classNames.add(type.getQualifiedName().toString());
        }

        if (packageName == null) {
            return false;
        }

        StringBuilder builder = new StringBuilder()
                .append("package " + packageName + ";\n\n")
                .append("import android.app.Activity;\n")
                .append("import com.quxiangtech.zrouter.RouteRegister;\n")
                .append("import java.util.Map;\n\n")
                .append("public class ModuleRouteRegister implements RouteRegister {\n\n")
                .append("@Override\n")
                .append("  public void register(Map<String, String> routes) {\n");

        for (int i = 0; i < names.length; i++) {
            builder.append("routes.put(" + "\"" + names[i] + "\"" + ", " + "\"" + classNames.get(i) + "\");");
        }

                builder.append("}")
                .append("}");

        try {
            JavaFileObject javaFileObject = processingEnv.getFiler().createSourceFile(packageName + ".ModuleRouteRegister");
            Writer writer = javaFileObject.openWriter();
            writer.write(builder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}