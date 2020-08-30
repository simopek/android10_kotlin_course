package com.example.helloworld

fun main() {

    var str = "Hello world";
    str = str.toUpperCase()

    /*
    val declares an immutable object

    val immutableStr = "Hello universe"
    immutableStr = immutableStr.toUpperCase()
    */

    print(str + "!")
}