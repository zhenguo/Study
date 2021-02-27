package com.quxiangtech.memory;

import android.app.Activity;

import java.io.IOException;

public class DumpMemoryTest extends Activity {
    static class A {
        public int a;

        public A(int a) {
            this.a = a;
        }
    }

    static class B {
        public int b;

        public B(int b) {
            this.b = b;
        }
    }

    // hprof-conv dump.hprof converted-dump.hprof 转换命令，要不然MAT打不开  platform-tools/
    // 在MAT工具里，查看柱形图，group by package

    /**
     * MAT中的重要概念
     * incoming是什么？  对象被谁持有
     * outcoming是什么？ 对象里面持有了谁
     * MAT中的深堆和浅堆
     * Shallow Heap: 浅堆  对象本身占有的内存空间
     * Retained Heap: 深堆 对象引用的对象占有的内存空间 是个统计结果
     */
    public static void dumpHprof(String filePath) throws IOException {
        android.os.Debug.dumpHprofData(filePath);
    }
}
