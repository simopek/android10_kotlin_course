package com.example.helloworld

/*
Abstract class VS Interface

An abstract class can have internal state.
A class can only extend an abstract class
while implementing multiple interfaces.

 */

abstract class Shape(
    val numberOfSides: Int
) {
    abstract fun area(): Double
}

class Rectangle(val l1: Double, val l2: Double) : Shape(4) {

    override fun area(): Double {
        return l1 * l2
    }

}

class Triangle(val base: Double, val height: Double) : Shape(3) {

    override fun area(): Double {
        return (base * height) / 2
    }

}

fun main() {

    var t1 = Triangle(200.5, 40.0)
    println("t1(base=${t1.base}, height=${t1.height}) has area=${t1.area()}")

    var r1 = Rectangle(200.5, 40.0)
    println("r1(l1=${r1.l1}, l2=${r1.l2}) has area=${r1.area()}")

    assert(t1.numberOfSides < r1.numberOfSides)
}