package com.example.helloworld

fun main() {

    val num1 = 312
    val num2 = 29

    // string interpolation
    println("$num1 is equal to $num2? ${num1 == num2}")

    var num3 = 123
    print("num3 was $num3; ")
    num3 += 120
    println("now it's $num3")

    var num4 = 567
    println("num4 is $num4")
    // the increment is done AFTER the interpolation
    println("num4 is ${num4++}")
    // the increment is done BEFORE the interpolation
    println("num4 is ${++num4}")
    // the decrement is done BEFORE the interpolation
    println("num4 is ${--num4}")

    // N.B. the expression with $ are templates
}