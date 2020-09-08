package com.example.helloworld

fun main() {

    //f1()
    //f2()
    //f3()
    f4()
    f5()
}

// example of safe cast operator
fun f5() {

    val myAnyVar = "example of safe cast operator"
    val myStringVar: String? = myAnyVar as? String
    // myIntVar's type is Int?
    val myIntVar = myAnyVar as? Int

    println("myStringVar->$myStringVar")
    println("myIntVar->$myIntVar")
}

// safe cast operator "as?"; null is returned if the cast fails
fun f4() {

    var myAnyVar: Any? = "a generic string"
    var myIntVar: Int? = myAnyVar as? Int?
}

fun f3() {

    var myAnyVar: Any? = "a generic string"
    // Exception in thread "main" java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
    var myIntVar: Int? = myAnyVar as Int?
}

fun f2() {

    var myNum = 2312
    // Exception in thread "main" java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
    var myString = myNum as String
}

fun f1() {

    var myAnyVar: Any? = null
    // Exception in thread "main" kotlin.TypeCastException: null cannot be cast to non-null type kotlin.Int
    var myIntVar: Int = myAnyVar as Int
}