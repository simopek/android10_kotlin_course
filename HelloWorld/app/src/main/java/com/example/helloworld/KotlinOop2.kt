package com.example.helloworld

import java.lang.IllegalArgumentException

fun main() {

    var c = Car()
    println("Car->(owner=${c.owner}, brand=${c.brand}, maxSpeed=${c.maxSpeed})")

    var c2 = Car()
    c2.maxSpeed = 301
    println("Car->(owner=${c2.owner}, brand=${c2.brand}, maxSpeed=${c2.maxSpeed})")

    var c3 = Car()
    // we cannot set the "model" property because the setter is private
    // c3.model = "Guia"
}

class Car() {

    // With "lateinit" we say the variable is initialized in the "init" block.
    // However, if we access the variable without it being initialized, we get an exception
    lateinit var owner: String

    val brand: String = "Pagani"
        get() {
            return field.toLowerCase()
        }

    // the getter and setter are what is
    // created by default
    var maxSpeed: Int = 340
        get() = field
        set(value) {
            field = if (value > 300) value else throw IllegalArgumentException("maxSpeed cannot be <= 300")
        }
    var model: String = "Hyuna"
        private set

    init {
        owner = "Me"
    }
}