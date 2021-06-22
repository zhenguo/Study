package com.qihoo.kotlin

class LanguageStudy { // 类默认为public
    // 静态内部类
    companion object {
        // 这里面的全是static
        private val instance = LanguageStudy()
        fun getInstance2(): LanguageStudy = instance
        fun getInstance3(): LanguageStudy = Holder.instance
    }

    // 嵌套类 ->静态内部类
    class Sub2 {

    }

    // 内部类
    inner class Sub {
        fun test() {
            println(a)

            // 生成的Java代码中testInner是个Function类型的变量
            fun testInner() {
                println(b)
            }

            testInner()
            println(b)
        }
    }

    // 静态对象
    object Holder {
        val instance = LanguageStudy()
    }

    var a: String = "A"
    val b: String = "B"
    var c = "AAA"
    val d = 'A'
    var age = 100

    fun test() {
        val instance = instance;
        getInstance2()
    }

    fun main(): Unit {
        println(add(1, 2))
        println(add2(1, 2, 3, 4, 5, 6, 7))
        outside()
        println("a: $a, b: $b")
        val newLine = """
            AAA
            BBB
            CCC
            EEE
        """.trimIndent()
        println(newLine)
        val money = """
            ${'$'}99.99
        """.trimIndent()
        println(money)
        var nullTest: String? = null
//        nullTest = "AAA"
//        println(nullTest?.length)
//        println(nullTest!!.length)
        println(nullTest?.length ?: "真的为null")

        for (i in 10 downTo 1) {
            println(i)
        }
        // 指定步长
        for (i in 10 downTo 1 step 2) {
            println(i)
        }

        for (i in 1 until 10) {
            println(i)
        }

        // 比较
        var s1: String = "AAA"
        var s2: String = "AAA"
        println(s1.equals(s2)) // kotlin推荐使用==
        println(s1 == s2) // 等价于Java中的equals，比较内存地址及值是否相等
        println(s1 === s2) // 比较内存地址

        var numbers = arrayOf(1, 2, 3, 4, 5, 6)
        var strList = arrayOf("1", "2", "3")

        var list = Array<Int>(10) { v: Int -> (v + 2000) } // v是index(0~9)
        var n1 = 199
        var n2 = 200
        var maxN = if (n1 > n2) n1 else n2
        var maxN2 = if (n1 > n2) { // 骚操作
            println("xxxxx")
            n1
        } else {
            println("xxxxx")
            n2
        }

        when (n1) {
            in 1..100 -> println("哈哈") // kotlin when的高级用法
            1 -> println("一")
            2 -> println("一")
            3 -> println("一")
            4 -> println("一")
            5 -> println("一")
            6 -> println("一")
            else -> println("Nothing")
        }
        var result: Any = when (n1) { // 混合返回结果使用 Any
            1 -> {
                "返回String"
            }
            2 -> {
                99
            }
            else -> {
                "啥也不是"
            }
        }
    }

    fun printList() {
        var list = listOf("张三", "李四", "王五")
        list.forEach {
            println(it)
        }
        list.indices.forEach {
            println(it)
        }
        for (index in list.indices) {
            println(index)
        }
    }

    fun printLable() {
        // 三种不同的打印方式
        // 生成的Java代码没啥区别，都是this.a
        println(a)
        println(this.a)
        println(this@LanguageStudy.a)
    }

    fun cycle() {
        for (i in 1..5) {
            for (j in 1..5) {
                println("i: $i, j: $j")
                if (i == 3) {
                    break // 仅break了j
                }
            }
        }

        // 使用标签
        jb@ for (i in 1..5) {
            for (j in 1..5) {
                println("i: $i, j: $j")
                if (i == 3) {
                    break@jb // 直接break掉所有循环
                }
            }
        }

        println("cycle end")
    }

    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun add1(a: Int, b: Int) = a + b

    private fun add2(vararg values: Int?) {
        for (value in values) {
            println(value)
        }
    }
}

fun outside() {

}