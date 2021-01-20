package com.quxiangtech.buildsrc;

import org.objectweb.asm.MethodVisitor;

public class ZRouterMethodVisitor extends MethodVisitor {
    public ZRouterMethodVisitor(int i) {
        super(i);
    }

    @Override
    public void visitMethodInsn(int i, String s, String s1, String s2, boolean b) {
        super.visitMethodInsn(i, s, s1, s2, b);


    }
}
