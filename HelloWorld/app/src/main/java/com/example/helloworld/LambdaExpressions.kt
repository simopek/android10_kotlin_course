package com.example.helloworld



fun main() {

    // The lambda's body is between the curly brackets.
    // Before the "= {" there's the lambda "signature".
    val add2Ints: (Int, Int) -> Int = {n1: Int, n2: Int -> n1 + n2}
    val add2IntsShortVersion = {n1: Int, n2: Int -> n1 + n2}

    val num1 = 1234
    val num2 = 11
    assert(add2Ints(num1, num2) == add2IntsShortVersion(num1, num2))
}