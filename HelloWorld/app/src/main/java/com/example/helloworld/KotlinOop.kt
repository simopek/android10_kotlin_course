package com.example.helloworld

fun main() {

    var p = Person3("John", "Willis")

    var p2 = Person3()
    p2.hobby = "running"
    p2.stateHobby()

    var p3 = Person3(lastname = "Phillis")
    p3.stateHobby()

    var p4 = Person3("Jim", "Bell", 34)
    p4.hobby = "sky-flying"
    p4.stateHobby()

    var phone1 = MobilePhone("android", "samsung", "note 10x")
    var phone2 = MobilePhone("android", "asus", "xd2")
    var phone3 = MobilePhone("osx", "apple", "iphone 1000")
}

// with the keyword "constructor" we can declare the visibility
class Person constructor(firstname: String, lastname: String) {

}

class Person2 public constructor(firstname: String, lastname: String) {

}

class Person3(firstname: String = "Default First Name", lastname: String = "Default Last Name") {

    // member properties/variables
    var firstname: String
    var lastname: String
    var age: Int? = null
    var hobby: String = "doing nothing"

    // The parameters firstname and lastname of the primary constructor are visible only
    // in the constructors and in the initializer block.
    // We have to save the values into variables like "age" and "hobby".

    // initializer
    init {

        this.firstname = firstname
        this.lastname = lastname

        println("initialized new Person object with firstname=$firstname lastname=$lastname")
    }

    // secondary constructor

    /*
    A secondary constructor needs to call
    the primary constructor.
    The initializer block is executed too.
     */

    constructor(
        firstname: String, lastname: String,
        age: Int
    ) : this(firstname, lastname) {
        this.age = age
        println("initialized new Person object with firstname=$firstname lastname=$lastname and age=$age")
    }

    // member functions/methods
    fun stateHobby() {
        println("$firstname $lastname's hobby is $hobby")
    }
}

class MobilePhone(osName: String, brand: String, model: String) {

    init {
        println("initialized new MobilePhone object - osName=$osName brand=$brand model=$model")
    }
}