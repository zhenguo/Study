package com.quxiangtech.buildsrc;

import org.apache.commons.io.IOUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

public class RegisterCodeGenerator {

    public static void insertInitCodeTo(List<String> registerList, File jarFile) throws IOException {
        File optJar = new File(jarFile.getParent(), jarFile.getName() + ".opt");
        if (optJar.exists()) {
            optJar.delete();
        }
        JarFile file = new JarFile(jarFile);
        Enumeration<JarEntry> enumeration = file.entries();
        JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(optJar));
        while (enumeration.hasMoreElements()) {
            JarEntry jarEntry = enumeration.nextElement();
            String entryName = jarEntry.getName();
            ZipEntry zipEntry = new ZipEntry(entryName);
            InputStream inputStream = file.getInputStream(jarEntry);
            jarOutputStream.putNextEntry(zipEntry);
            if (entryName.equals("com/quxiangtech/zrouter/ZRouter.class")) {
                byte[] bytes = referHackWhenInit(registerList, inputStream);
                jarOutputStream.write(bytes);
            } else {
                jarOutputStream.write(IOUtils.toByteArray(inputStream));
            }
            inputStream.close();
            jarOutputStream.closeEntry();
        }
        jarOutputStream.close();
        file.close();
        if (jarFile.exists()) {
            jarFile.delete();
        }
        optJar.renameTo(jarFile);
    }

    private static byte[] referHackWhenInit(List<String> registerList, InputStream inputStream) throws IOException {
        ClassReader cr = new ClassReader(inputStream);
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);
        ClassVisitor cv = new MyClassVisitor(Opcodes.ASM5, cw, registerList);
        cr.accept(cv, ClassReader.EXPAND_FRAMES);
        return cw.toByteArray();
    }

    public static class MyClassVisitor extends ClassVisitor {

        private List<String> registerList;

        MyClassVisitor(int api, ClassVisitor cv, List<String> registerList) {
            super(api, cv);
            this.registerList = registerList;
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc,
                                         String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            if (name.equals("registerRouter")) {
                mv = new RouteMethodVisitor(Opcodes.ASM5, mv, registerList);
            }
            return mv;
        }
    }

    static class RouteMethodVisitor extends MethodVisitor {

        private List<String> registerList;

        RouteMethodVisitor(int api, MethodVisitor mv, List<String> registerList) {
            super(api, mv);
            this.registerList = registerList;
        }

        @Override
        public void visitInsn(int opcode) {
            if (opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) {
                for (String name : registerList) {
                    LogUtil.println("visitInsn======name: " + name);
                    //创建 对应类型对象
                    mv.visitTypeInsn(Opcodes.NEW, name);
                    // 复制栈顶 因为INVOKESPECIAL 会消耗一个
                    mv.visitInsn(Opcodes.DUP);
                    mv.visitMethodInsn(Opcodes.INVOKESPECIAL, name, "<init>", "()V",false);
                    mv.visitVarInsn(Opcodes.ASTORE, 1);
                    mv.visitVarInsn(Opcodes.ALOAD, 1);
                    mv.visitVarInsn(Opcodes.ALOAD, 0);
                    mv.visitFieldInsn(Opcodes.GETFIELD, "com/quxiangtech/zrouter/ZRouter", "mRoutes", "Ljava/util/Map;");
                    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, name, "register", "(Ljava/util/Map;)V",false);
                }
                //把registerByPlugin 设置为 true
                mv.visitInsn(Opcodes.ICONST_1);
                mv.visitFieldInsn(Opcodes.PUTSTATIC, "com/quxiangtech/zrouter/ZRouter", "routersRegistered", "Z");
            }
            super.visitInsn(opcode);
        }
    }


}
