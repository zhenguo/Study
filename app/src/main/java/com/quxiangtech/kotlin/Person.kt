package com.quxiangtech.kotlin

open class Person(id: Int) { // 类的主构造函数,类默认为public final , 加上open会去掉open,就可以继承了
    constructor() : this(100) { // 子构造函数必须调用主构造函数

    }

    constructor(id: Int, char: Char) : this(id) {

    }

    constructor(id: Int, name: String) : this() {

    }

    fun main() {
        var emptyMain = Person(100, "Jack")
    }
}