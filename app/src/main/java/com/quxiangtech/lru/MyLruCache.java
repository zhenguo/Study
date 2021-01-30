package com.quxiangtech.lru;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.LinkedList;

public class MyLruCache<E> {
    private LinkedList<ListItem<E>> mData = new LinkedList<>();

    public MyLruCache(int initSize) {

    }

    public void put(String key, E value) {
        mData.add(0, new ListItem<>(key, value));
    }

    public @Nullable
    E get(String key) {
        E result = null;
        for (int i = 0; i < mData.size(); i++) {
            if (TextUtils.equals(key, mData.get(i).key)) {
                result = mData.get(i).value;
                mData.add(0, null);
                mData.set(0, mData.get(i));
                break;
            }
        }

        /**
         * 处理命中
         */

        return result;
    }

    static class ListItem<E> {
        String key;
        E value;

        public ListItem(String key, E value) {
            this.key = key;
            this.value = value;
        }
    }
}
