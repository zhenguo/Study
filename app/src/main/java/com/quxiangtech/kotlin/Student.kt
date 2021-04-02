package com.quxiangtech.kotlin

class Student(id: Int) : Person(id), KotlinInterface {
    // 成员变量必须赋值
    var name: String
        get() {
            TODO()
        }
        set(value) {}

    fun testDataClass() {
        var dataClass = WeatherData("北京", 23.3f)
        var copy = dataClass.copy(location = "天津", maxTemp = 23.3f)
        var (loaction, temp) = dataClass.copy()
        var (_, temp2) = dataClass.copy() // _表示拒收
        println("location: $loaction, temp: $temp, temp2: $temp2")
    }

    override fun onSuccess(): String = "sss"
//    var name:String = ""
//    var name: String = "AAA"
//    var name: String? = null
//    lateinit var name:String
}