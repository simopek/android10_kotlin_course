package com.example.helloworld

fun main() {

    var bmwM1 = Car2("M1", "BMW", 2000)
    var teslaS = ElectricCar("S", "tesla", 45.2, 1600)

    bmwM1.drive(100.0)
    teslaS.drive(50.2)

    bmwM1.switchEngineOn()
    println("bmwM1 has engine ${if (bmwM1.isEngineOn()) "ON" else "OFF" }")
    bmwM1.switchEngineOff()
    println("bmwM1 has engine ${if (bmwM1.isEngineOn()) "ON" else "OFF" }")

}

/*
In Kotlin all classes are "final" by default.
So we have to declare a class "open" in order
to make it inheritable.

Any class inherits from the Any class; this class defines
toString, hashCode and equals.

Variables and methods need to be defined "open" in order
to let the child classes override them.

An interface is a contract that obliges a class
to implement properties and methods.
 */

interface HasEngine {
    // dimension in cm^3
    val cm3Dimension: Int
    fun switchEngineOn()
    fun switchEngineOff()
    fun isEngineOn(): Boolean
    fun isBigEngine(): Boolean {
        return cm3Dimension > 2000
    }

}

// super class or base class or parent class
open class Vehicle() {

}

// subclass or child class or derive class
open class Car2(name: String, brand: String, override val cm3Dimension: Int) : Vehicle(),
    HasEngine {

    val name: String = name
    val brand: String = brand
    private var engineOn: Boolean = false

    open fun drive(distance: Double) {
        println("car($name, $brand) drove for $distance KM")
    }

    override fun switchEngineOn() {
        engineOn = true
    }

    override fun switchEngineOff() {
        engineOn = false
    }

    override fun isEngineOn(): Boolean {
        return engineOn
    }
}

class ElectricCar(name: String, brand: String, battery: Double, cm3Dimension: Int) : Car2(
    name, brand,
    cm3Dimension
) {

    override fun drive(distance: Double) {
        super.drive(distance)
        println("discharging battery")
    }
}