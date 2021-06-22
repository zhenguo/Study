package com.qihoo.asmTest;


public class ASMTest {
    public static void main(String[] args) {
        ASMTest asmTest = new ASMTest();
        asmTest.test();
    }

    public void test() {
    }

//    static class MyClassVisitor extends ClassVisitor {
//
//        public MyClassVisitor(int api) {
//            super(api);
//        }
//
//        @Override
//        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
//            System.out.println("visitMethod: " + name);
//            return super.visitMethod(access, name, descriptor, signature, exceptions);
//        }
//
//    }
//
//    static class MyMethodVisitor extends MethodVisitor {
//
//
//        public MyMethodVisitor(int api, MethodVisitor methodVisitor) {
//            super(api, methodVisitor);
//        }
//
//        @Override
//        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
//            return super.visitAnnotation(descriptor, visible);
//        }
//
//
//    }
}
