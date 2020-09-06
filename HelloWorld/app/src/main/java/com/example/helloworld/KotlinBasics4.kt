package com.example.helloworld

fun main() {

    for (num in 1..10) {
        println("$num")
    }

    // range is [1,10)
    for (num in 1 until 10) {
        println("$num")
    }

    // range is [10,1]
    for (num in 10 downTo 1) {
        println("$num")
    }

    for (num in 10 downTo 1 step 2) {
        print("$num ")
    }
    println()

    // the above "for" loop is the same as this one
    for (num in 10.downTo(1).step(2)) {
        print("$num ")
    }
    println()

    println("________________________")

    for (x in 0..10000) {

        if (x == 9001) {
            println("It's over 9000")
        }
    }

    println("________________________")

    var humidityLevel = 80
    var humidity = "humid"

    while (humidity == "humid") {
        humidityLevel -= 5
        println("humidity decreased")

        if (humidityLevel < 60) {
            humidity = "comfy"
            println("it's comfy now")
        }
    }

}