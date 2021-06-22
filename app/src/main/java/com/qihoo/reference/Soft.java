package com.qihoo.reference;

import java.lang.ref.SoftReference;

public class Soft {

    public static void main(String[] args) {
        SoftReference<Integer> integerSoftReference = new SoftReference<>(1);
    }
}
