package com.quxiangtech.buildsrc;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ZRouterClassVisitor extends ClassVisitor {
    public ZRouterClassVisitor(int i) {
        super(i);
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {

        ZRouterMethodVisitor visitor = new ZRouterMethodVisitor(Opcodes.ASM7);
        LogUtil.println("i: " + i);
        LogUtil.println("s: " + s);
        LogUtil.println("s1: " + s1);
        LogUtil.println("s2: " + s2);

        if (strings != null) {
            for (int j  = 0; j < strings.length; j++) {
                LogUtil.println("strings[" + j + "]: " + strings[j]);
            }
        }

        LogUtil.println("\n\n");
        return visitor;
    }
}
