package com.quxiangtech.myapplication;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ASMTest {
    @Test
    public void test() {
        try {
            FileInputStream fis = new FileInputStream("class1");
            ClassReader cr = new ClassReader(fis);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);

            cr.accept(new ClassVisitor(Opcodes.ASM7), ClassReader.EXPAND_FRAMES);
            FileInputStream fos = new FileInputStream("outputclass");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClassVisitor extends org.objectweb.asm.ClassVisitor {

        public ClassVisitor(int api) {
            super(api);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }
    }

    static class MethodVisitor extends org.objectweb.asm.MethodVisitor {

        public MethodVisitor(int api) {
            super(api);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        }
    }
}
