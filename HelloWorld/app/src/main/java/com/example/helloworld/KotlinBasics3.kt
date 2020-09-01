package com.example.helloworld

fun main() {

    var years = 80

    when (years) {
        50 -> println("Elvis & co.")
        60 -> println("Beatles & co.")
        70 -> println("Pink Floyd")
        90 -> println("Robbie Williams")
        80 -> {
            println("Stevie Wonder")
            println("Queen")
        }
        else -> println("other music artists")
    }

    var age = 20

    // 'when' behaves like a if-else block, so only a path is executed
    when {
        age >= 21 -> println("you may drink in the US")
        age >= 18 -> println("you may vote now")
        age >= 16 -> println("you may drive in the US")
        age < 15 -> println("you're too young")
    }

    // in this case we use the 'ranges'
    when (age) {
        16, 17 -> println("you may drive in the US")
        in 18..20 -> println("you may vote now")

        //in 21..1000 -> println("you may drink in the US")
        !in 0..20 -> println("you may drink in the US")

        else -> println("you're too young")
    }

    var x: Any = 123.21f

    when (x) {
        is Int -> println("$x is an Int")
        is Double -> println("$x is a Double")
        is String -> println("$x is a String")
        else -> println("$x is something unknown")
    }
}