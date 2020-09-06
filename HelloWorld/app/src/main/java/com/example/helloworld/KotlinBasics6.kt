package com.example.helloworld

fun main() {

    var myString: String = "Hello!!!"
    myString = "Hola!"
    // COMPILATION ERROR - we cannot assign NULL to a variable of this type
    //myString = null

    // with the question mark we say the variable CAN accept "null"
    var myNullableString: String? = "Hello!!!"
    myNullableString = "Hola!"
    myNullableString = null

    var myStringLen = myString.length
    // COMPILATION ERROR because we are handling a nullable
    //var myNullableStringLen = myNullableString.length

    if (myNullableString != null) {
        myNullableString.length
    } else {
        null
    }

    // this line is equivalent to the "old" if-else above
    var myNullableStringLen: Int? = myNullableString?.length
    // prints out null
    println(myNullableStringLen?.toString())
    // N.B. ? is called safe-call operator

    // with "let" and the safe-call operator we can have
    // some code to be executed only if the variable is not null
    myNullableString = "Hello!"
    myNullableString?.let { println(myNullableString.toString()) }
}