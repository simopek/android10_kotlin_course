package com.example.helloworld

fun main() {

    // examples of type casting

    var v1: Any = "A generic string"

    if (v1 !is String) {
        println("v1 is not a string")
    } else {
        // SMART CAST
        // the compiler already knows that in this case
        // the object must be a string, so it casts the object

        println("v1 is a string with length=${v1.length}")
    }

    // UNSAFE CASTING
    // if v2Any wasn't a string, we'd get a "class cast exception"
    var v2Any: Any = "231.0"
    var v2 = v2Any as String

    var v3Any: Any = 414.22
    // SAFE CAST
    // If the cast fails, we get a null object.
    // The returned object is a "nullable"; for this reason we use
    // the "safe call" operator.
    var v3 = v3Any as? String
    println("v3 is $v3 with length=${v3?.length}")
}