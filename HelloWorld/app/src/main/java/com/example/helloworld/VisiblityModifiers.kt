package com.example.helloworld

/*

4 visibility modifiers:
- public
- private
- protected
- internal

- public
    - it's the default
    - the member (property, method, ...) is accessible in the project
- private
    - the member is only accessible in the block in which it's defined
- internal
    - the member is accessible only in the module where it's implemented
- protected
    -the member is accessible by the subclasses


Open keyword
- it's how we let the class be inherited
- in Kotlin the classes and members are final (the opposite of what happens in Java)
 */

fun main() {

}

open class Base() {

    val a: Int = 1
    private val b: Int = 2
    protected open val c: Int = 3
    protected val c2: Int = 32
    internal val d: Int = 4
}

class Derived() : Base() {

    // "c2 cannot be overridden because it's not "open"
    // override val c2: Int = 42
    override val c: Int = 33

    fun printc2() {

        this.a
        // "b" is not visible because is private
        //this.b
        this.d
        println("c2->${c2}")
    }
}