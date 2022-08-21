package emg.example.kotlinbasics

fun main() {
    val numbers: IntArray = intArrayOf(1, 2, 3, 4, 5, 6)
    println(numbers)
    println(numbers.contentToString())

    for (element in numbers) {
        print("${element + 2} ")
    }
    println()
    println("initial values ${numbers.contentToString()}")
    numbers[0] = 7
    numbers[5] = 1
    numbers[4] = 5
    println("modified values ${numbers.contentToString()}")

    val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    println(days.contentToString())

    val fruits = arrayOf(Fruit("Apple", 0.25), Fruit("Orange", 0.35), Fruit("Banana", 0.15))
    println(fruits.contentToString())

    fruits.forEach { println(it.name) }

    println("###########Lists###########")
    val months = listOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
    )

    println(months.size)
    println(months[3])
    val additionalMonths = months.toMutableList()
    additionalMonths.add("December")

    val weekDays =
        mutableListOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
    weekDays.removeAt(0)
    println(weekDays.toTypedArray().contentToString())


    println("###########Sets###########")
    val fruitSet = setOf("Orange", "Apple", "Banana", "Orange", "Apple", "Banana")
    println(fruitSet.size)

    val newFruits = fruitSet.toMutableSet()
    newFruits.add("Kiwi")
    println(newFruits.size)


    val dayOfWeek = mapOf(
        1 to "Monday",
        2 to "Tuesday",
        3 to "Wednesday",
        4 to "Thursday",
        5 to "Friday",
        6 to "Saturday",
        7 to "Sunday"
    )

    println(dayOfWeek)
    for (key in dayOfWeek.keys) {
        println("$key - ${dayOfWeek[key]}")
    }

    val newDayOfWeek = dayOfWeek.toMutableMap().toSortedMap()
    newDayOfWeek[8] = "NonExistentDay"
    println(newDayOfWeek)

    val fruitMap =
        mapOf(1 to Fruit("Apple", 0.25), 2 to Fruit("Orange", 0.35), 3 to Fruit("Banana", 0.15))
    println(fruitMap)

    val numberList = mutableListOf(1, 2, 3, 4, 5, 6)
    var adder: Int = 0
    for (number in numberList) {
        adder += number
    }
    val avg: Double = adder.toDouble() / numberList.size
    println(avg)

    val sum: (Int, Int) -> Int = { x, y -> x + y }
    println(sum(1, 2))
}

data class Fruit(val name: String, val price: Double)