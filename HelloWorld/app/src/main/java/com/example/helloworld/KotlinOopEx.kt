package com.example.helloworld

fun main() {

    var phone = MobilePhone2("android", "samsung", "galaxy 1000")
    phone.chargeBattery(30)
}

class MobilePhone2(osName: String, brand: String, model: String) {

    val MAX_BATTERY_CHARGE = 50
    var battery: Int

    init {

        battery = (0..MAX_BATTERY_CHARGE).shuffled().first()

        println("The phone $model from $brand uses $osName as its Operating System")
    }

    fun chargeBattery(charge: Int) {

        var safeCharge =
            if (charge <= 0) 0 else if (charge > MAX_BATTERY_CHARGE) MAX_BATTERY_CHARGE else charge

        val oldCharge = battery
        var newCharge = oldCharge + safeCharge
        if (newCharge > MAX_BATTERY_CHARGE) {
            newCharge = MAX_BATTERY_CHARGE
        }

        this.battery = newCharge

        println("battery was $oldCharge; it was charged by $charge; now the battery is ${this.battery}")
    }
}