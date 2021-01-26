package com.quxiangtech.myapplication;

import org.junit.Test;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ASMTest {
    @Test
    public void test() {
        try {
            FileInputStream fis = new FileInputStream("src\\test\\java\\com\\quxiangtech\\myapplication\\APTTest.java");
            ClassReader cr = new ClassReader(fis);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);

            cr.accept(new MyClassVisitor(Opcodes.ASM6), ClassReader.EXPAND_FRAMES);
            FileOutputStream fos = new FileOutputStream("D:\\code\\git\\Study\\app\\src\\test\\java\\com\\quxiangtech\\myapplication\\APTTest_g.java");
            fos.write(cw.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class MyClassVisitor extends ClassVisitor {

        public MyClassVisitor(int api) {
            super(api);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            System.out.println("visitMethod: " + name);
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }

    }

    static class MyMethodVisitor extends MethodVisitor {


        public MyMethodVisitor(int api, MethodVisitor methodVisitor) {
            super(api, methodVisitor);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            return super.visitAnnotation(descriptor, visible);
        }


    }
}
