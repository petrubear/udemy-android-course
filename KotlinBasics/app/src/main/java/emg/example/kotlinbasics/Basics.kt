package emg.example.kotlinbasics

fun main() {
    var myName = "John"
    myName = "John Doe"
    print("Hello $myName")

    println("##########Data Types##########")
    // data types
    val string: String = "Android Masterclass"
    val float: Float = 13.37F
    val double: Double = 3.14159265358979
    val byte: Byte = 25
    val short: Short = 2020
    val long: Long = 18881234567
    val boolean: Boolean = true
    val char: Char = 'a'

    println("$string $float $double $byte $short $long $boolean $char")
    println("$string length is ${string.length}")

    println("##########When##########")
    // statements
    var season = 3
    when (season) {
        1 -> println("Winter")
        2 -> println("Spring")
        3 -> println("Summer")
        4 -> {
            println("Fall")
            println("Autumn")
        }
        else -> println("Invalid season")
    }

    var month = 3
    when (month) {
        1, 2, 3 -> println("Winter")
        4, 5, 6 -> println("Spring")
        7, 8, 9 -> println("Summer")
        10, 11, 12 -> {
            println("Fall")
            println("Autumn")
        }
        else -> println("Invalid month")
    }
    when (month) {
        in 3..5 -> println("Spring")
        in 6..8 -> println("Summer")
        in 9..11 -> println("Fall")
        12, 1, 2 -> println("Winter")
        else -> println("Invalid month")
    }

    var age = 19
    when (age) {
//        in 21..150 -> println("You may drink now")
        !in 0..20 -> println("You may drink now")
        in 18..20 -> println("You may vote now")
        in 16..17 -> println("You may drive now")
        else -> println("You are too young")
    }

    var x: Any = 13.37f
    val result = when (x) {
        is Int -> "is an integer"
        is Float -> "is a float"
        is Double -> "is a double"
        else -> "is something else"
    }

    println("$x $result")

    println("##########Loops##########")
    var number = 100
    while (number >= 0) {
        println("$number")
        number -= 2
    }

    for (num in 1..10) {
        print("$num")
    }
    println()
    for (num in 1 until 10) {
        print("$num ")
    }

    for (num in 10 downTo 1) {
        print("$num ")
    }
    println()
    for (num in 10 downTo 1 step 2) {
        print("$num ")
    }


    println("##########Functions##########")
    myFunction()
    println("addUp: ${addUp(3, 7)}")

    println("##########Nullables##########")
    var nullable: String? = null
    //nullable = "Hello"

    nullable?.let {
        println("nullable length: ${it.length}")
    }
    val len = nullable?.length ?: -1
    println("nullable length: $len")


    val name = nullable ?: "John Doe"
    println("name: $name")
    //nullable!!.toLowerCase()
}

fun myFunction() {
    println("Hello from myFunction")
}

fun addUp(a: Int, b: Int): Int {
    return a + b
}

