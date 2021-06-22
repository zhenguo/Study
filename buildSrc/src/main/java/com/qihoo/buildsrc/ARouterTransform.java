package com.qihoo.buildsrc;


import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;
import com.android.utils.FileUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.gradle.api.Project;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Set;
import java.util.function.Consumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ARouterTransform extends Transform {

    private Project mProject;
    static ArrayList<String> registerList = new ArrayList<>();
    static File destFile;

    public ARouterTransform(Project project) {
        mProject = project;
    }

    @Override
    public String getName() {
        return "ZRouterTransform";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);

        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();
        outputProvider.deleteAll();

        Collection<TransformInput> transformInputs = transformInvocation.getInputs();
        for (TransformInput input : transformInputs) {
            Collection<DirectoryInput> directoryInputs = input.getDirectoryInputs();
            processDirectoryInputs(directoryInputs, outputProvider);

            Collection<JarInput> jarInputs = input.getJarInputs();
            processJarInputs(jarInputs, outputProvider);
        }

        if (destFile != null) {
            RegisterCodeGenerator.insertInitCodeTo(registerList, destFile);
        }
    }

    private void processJarInputs(Collection<JarInput> jarInputs, TransformOutputProvider provider) {
        for (JarInput jarInput : jarInputs) {
            String destName = jarInput.getName();
            File src = jarInput.getFile();
            String hexName = DigestUtils.md5Hex(src.getAbsolutePath());
            //去掉.jar
            if (destName.endsWith(".jar")) {
                destName = destName.substring(0, destName.length() - 4);
            }
            //处理完成后的输出目录
            File dest = provider.getContentLocation(destName + "_" + hexName, jarInput.getContentTypes(),
                    jarInput.getScopes(), Format.JAR);

            try {
                //处理jar包
                scanJar(src, dest);
                org.apache.commons.io.FileUtils.copyFile(src, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void scanJar(File src, File dest) throws IOException {
        JarFile file = new JarFile(src);
        Enumeration<JarEntry> enumeration = file.entries();
        while (enumeration.hasMoreElements()) {
            JarEntry jarEntry = enumeration.nextElement();
            String entryName = jarEntry.getName();
            //apt生成类的包名：扫描apt生成的类，需要插桩进入Router注册
            if (entryName.equals("com/quxiangtech/zrouter/ZRouter.class")) {
                //找到插桩目标类
                destFile = dest;
                break;
            }
        }
        file.close();
    }

    private void processDirectoryInputs(Collection<DirectoryInput> directoryInputs, TransformOutputProvider provider) {
        for (DirectoryInput input : directoryInputs) {
            File target = provider.getContentLocation(input.getName(), input.getContentTypes(), input.getScopes(), Format.DIRECTORY);
            String root = input.getFile().getAbsolutePath();
            if (!root.endsWith(File.separator)) {
                root += File.separator;
            }

            String finalRoot = root;
            FileUtils.getAllFiles(input.getFile()).forEach(new Consumer<File>() {
                @Override
                public void accept(File file) {
                    //去掉目录，得到包名+类名
                    String path = file.getAbsolutePath().replace(finalRoot, " ");
                    path = path.replaceAll("\\\\", "/");

                    processFile(file);
                }
            });

            try {
                org.apache.commons.io.FileUtils.copyDirectory(input.getFile(), target);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processFile(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ClassReader classReader = new ClassReader(fileInputStream);
            classReader.accept(new ClassVisitor(Opcodes.ASM7) {
                @Override
                public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                    super.visit(version, access, name, signature, superName, interfaces);

                    if (interfaces != null) {
                        for (String interfaceName : interfaces) {
                            if (interfaceName.equals("com/quxiangtech/zrouter/RouteRegister")) {
                                // 记录需要注册的apt生成类
                                if (!registerList.contains(name)) {
                                    registerList.add(name);
                                }
                            }
                        }
                    }
                }
            }, ClassReader.EXPAND_FRAMES);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
