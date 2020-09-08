package com.example.helloworld

import kotlin.random.Random

fun main() {

    val listSize = 5
    val numbersList = ArrayList<Double>(listSize)

    for(i in 1..listSize) {
        numbersList.add(randomNumber())
    }

    var numbersAvg = 0.0
    for (num in numbersList) {
        numbersAvg += num
    }
    numbersAvg /= numbersList.size

    println("numbersList->${numbersList}")
    println("numbersAvg->${numbersAvg}")
}

fun randomNumber(): Double {
    return Random.nextDouble(0.0, 20.0)
}