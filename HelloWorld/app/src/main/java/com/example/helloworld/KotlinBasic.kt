package com.example.helloworld

fun main() {

    var str = "Hello world";
    str = str.toUpperCase()

    /*
    val declares an immutable object

    val immutableStr = "Hello universe"
    immutableStr = immutableStr.toUpperCase()
    */

    println(str + "!")

    // all Integer types
    var myByte: Byte = 12
    var myInt: Int = 2141
    var myShort: Short = 21
    var myLong: Long = 123_141_421_21_1
    /*
    we can leave out the type if we initialize the variable because
    there's the type inference
    */

    // without the F at the end, the type would be Double
    var myFloat: Float = 2141.2F

    var letterChar = 'A'
    var digitChar = '1'

    val yourName = "Hudson"
    val yourNameFirstChar = yourName[0]
    println("Your name is " + yourName + "; first char is " + yourNameFirstChar)
}