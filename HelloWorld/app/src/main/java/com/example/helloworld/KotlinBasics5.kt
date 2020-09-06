package com.example.helloworld

fun main() {

    var num1 = 3
    var num2 = 5

    println("num1=$num1 num2=$num2")

    // addUp is called by providing it with arguments
    println("\taddUpResult is ${addUp(num1, num2)}")
    println("\tnumbersAvg is ${numbersAvg(num1, num2)}")
}

// addUp is defined to accept two parameters
fun addUp(a: Int, b: Int) : Int {
    return a + b
}

fun numbersAvg(a: Int, b: Int): Double {
    return (a + b) / 2.0
}