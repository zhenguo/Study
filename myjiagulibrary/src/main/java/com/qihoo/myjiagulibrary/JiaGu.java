package com.qihoo.myjiagulibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class JiaGu {
    private static byte[] encrpt(byte[] key) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
            byte[] encrypted = cipher.doFinal();
            return encrypted;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static File encryptAPKFile(File srcAPKFile, File dstAPKFile) {
        if (srcAPKFile == null) {
            return null;
        }

//        Zip.unzip(srcAPKFile, dstAPKFile);

        File[] dexFiles = dstAPKFile.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                return s.endsWith(".dex");
            }
        });

        File mainDexFile = null;
        byte[] mainDexData = null;

        for (File dexFile : dexFiles) {
            try {
                // 读数据
                FileInputStream fis = new FileInputStream(dexFile);
                int len = fis.available();
                byte[] buffer = new byte[len];
                int readLen = fis.read(buffer);
                fis.close();
                System.out.println("readLen: " + readLen);
                byte[] encryptedBuffer = encrpt(buffer);

                if (dexFile.getName().endsWith("classes.dex")) {
                    mainDexData = encryptedBuffer;
                    mainDexFile = dexFile;
                }

                // 写数据
                FileOutputStream fos = new FileOutputStream(dexFile);
                fos.write(encryptedBuffer);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return mainDexFile;
    }

    public static void unzip(File srcFile, File dstFile) {

    }

    public static void dxCommand(File aarDex, File classedJar) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("dx");
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static File jar2dex(File aarFile) {
        File fakeDex = new File(aarFile.getParent() + File.separator + "temp");
        System.out.println("jar2Dex: fakeDex " + fakeDex.getPath());

        unzip(aarFile, fakeDex);
        File[] files = fakeDex.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                return s.equals("classes.jar");
            }
        });
        if (files == null || files.length == 0) {
            return null;
        }

        File classes_jar = files[0];
        // 将classes.jar变成classes.dex
        File aarDex = new File(classes_jar.getParentFile(), "classes.dex");

        dxCommand(aarDex, classes_jar);
        return aarDex;
    }

    public static void performJiaGu() {
        /**
         * 第一步，处理原始APK， 加密dex
         */
        File apkFile = new File("source/apk/app-debug.apk");
        File newApkFile = new File(apkFile.getParent() + File.separator + "temp");
        if (!newApkFile.exists()) {
            newApkFile.mkdirs();
        }
        File mainDexFile = encryptAPKFile(apkFile, newApkFile);

        /**
         * 第二步，处理aar，获得壳dex
         */
        File aarFile = new File("source/aar/mylib/xxx-debug.aar");
//        File aarDex = Dx.jar2dex(aarFile);

        File tempMainDex = new File(newApkFile.getPath() + File.separator + "classes.dex");
        if (!tempMainDex.exists()) {

        }
    }
}
