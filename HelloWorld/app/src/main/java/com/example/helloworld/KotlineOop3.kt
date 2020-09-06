package com.example.helloworld

fun main() {

    var u1 = User(2, "Zio Paperone")
    u1.fullName = u1.fullName.toUpperCase()
    // we cannot change "id" because the class parameter is "val"
    // u1.id = 4

    var u2 = User(2, fullName = "Zio Paperino")

    assert(u1 != u2)
    u2.fullName = u1.fullName
    assert(u1 == u2)

    println("u1->$u1")


    // we copy all the properties except for "id"
    var copyOfUser2 = u2.copy(id = 5)
    println("copyOfUser2->$copyOfUser2")
    println("u2->$u2")

    // id
    println("u2.component1->${u2.component1()}")
    // fullName
    println("u2.component2->${u2.component2()}")

    // decomposition
    val (u2Id, u2FullName) = u2
}

/*
- the constructor must at least have one parameter
- for each parameter we have to use "val" or "var"
- if a parameter is a "var", we can update the inner property
- if a parameter is a "val", we cannot update the inner property

 */
data class User(val id: Int, var fullName: String)