package com.qihoo.kotlin

import android.app.Activity
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity

sealed class SetterTest { // 抽象类
    val s = "xxx"

    // 对应Java中的内部类
    class Sub {
        fun sub() {

        }
    }

    // 对应Java中的静态内部类
    inner class Sub2 {
        fun sub2() {
            println(s)
        }
    }

    // 对应Java中的静态内部类，但提供单例访问
    object Proxy {

    }

    // 静态内部类,在这里定义的成员都是静态的
    companion object {
        val s1 = "bbb"
//        val activity: Bitmap? = null
        fun companionFunction() = "I am a companion function"
        val activity: AppCompatActivity? = null
    }

    fun main() {
        println(companionFunction())
    }
//    fun test() {
//        val proxy = Proxy;
//
//    }
//
//    var i: Int? = null
//        //        get() {
////            return field
////        }
//        set(value) {
//            field = if (value!! > 100) {
//                value
//            } else
//                value
//        }
}