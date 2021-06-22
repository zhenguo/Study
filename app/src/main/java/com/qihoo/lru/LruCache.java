package com.qihoo.lru;

public class LruCache<T> {
    private int mMaxSize;
    private LinkedList<Entry<T>> mCache;
    private int mCount = 0;

    public LruCache(int maxSize) {
        mMaxSize = maxSize;
        mCache = new LinkedList<>();
    }

    public static void main(String[] args) {
        LruCache<Integer> integerLruCache = new LruCache<>(10);
        integerLruCache.put("test", 1);
        integerLruCache.put("test1", 2);
        integerLruCache.put("test2", 3);
        integerLruCache.put("test3", 4);
        integerLruCache.put("test4", 5);
        integerLruCache.put("test5", 6);
        integerLruCache.put("test6", 7);
        integerLruCache.put("test7", 8);
        integerLruCache.put("test8", 9);
        integerLruCache.put("test9", 10);
        integerLruCache.put("test10", 11);

        integerLruCache.toString();

//        System.out.println("get: " + integerLruCache.get("test"));
//        integerLruCache.toString();
    }

    @Override
    public String toString() {
        for (int i = 0; i < mCache.size; i++) {
            Entry<T> entry = mCache.get(i);
            System.out.println("entry " + i + " : " + "key: " + entry.key + " value: " + entry.value);
        }
        return "";
    }

    public void put(String key, T value) {
        System.out.println("curr cache size: " + mCache.size);
//        if (mCache.size == mMaxSize) {
//            mCache.removeLast();
//            toString();
//        }

        Entry<T> entry = new Entry<>(key, value);
        mCache.add(entry);
    }

    public T get(String key) {
        if (key == null) {
            return null;
        }
        Entry<T> entry = null;
        int position = -1;
        T result = null;
        for (int i = 0; i < mCache.size; i++) {
            Entry<T> value = mCache.get(i);
            if (key.equals(value.key)) {
                result = value.value;
                entry = value;
                position = i;
                mCount++;
                break;
            }
        }

        // 处理命中
        if (entry != null && position != -1) {
            mCache.add(entry);
            toString();
            mCache.remove(position + 1);
            System.out.println("after remove: " + position);
            toString();
            System.out.println("命中计数: " + mCount);
        } else {
            System.out.println("未命中");
        }

        return result;
    }

    class Entry<T> {
        String key;
        T value;

        @Override
        public String toString() {
            return "Entry{" +
                    "key='" + key + '\'' +
                    ", value=" + value +
                    '}';
        }

        public Entry(String key, T value) {
            this.key = key;
            this.value = value;
        }
    }
}
