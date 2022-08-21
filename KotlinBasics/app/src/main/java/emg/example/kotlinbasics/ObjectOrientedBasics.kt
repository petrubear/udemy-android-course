package emg.example.kotlinbasics

fun main() {


    println("##########Classes##########")
    val phone = MobilePhone("Android", "Samsung", "Note 20")
    val john = Person()
    val edison = Person("Edison", "Doe", 40)
    val jane = Person(firstName = "Jane")
    jane.age = 30
    jane.hobby = "play guitar"

    edison.displayHobby()
    jane.displayHobby()

    println("##########LateInit/Getters##########")
    val myCar = Car()
    //myCar.myModel = "Corolla"
    println("${myCar.myBrand} ${myCar.myModel} max speed is ${myCar.maxSpeed}")

    println("##########DataClass##########")
    val user1 = User(1, "John")
    val name = user1.name
    println(name)
    user1.name = "Michael"
    val user2 = User(1, "Michael")
    println(user1 == user2)
    println("User details are: $user1")
    val user3 = user1.copy(name = "Jane")
    println("User details are: $user3")
    println(user3.component1())
    println(user3.component2())

    val (uid, uname) = user3
    println("User details are: $uid, $uname")

    val phone1 = MobilePhone("Android", "Samsung", "Note 20")
    phone1.chargeBattery(25)

    println("##########Inheritance##########")
    val electricCar = ElectricCar(300.0)
    electricCar.drive(200.0)
    electricCar.brake()
}

class Person constructor(val firstName: String = "John", lastName: String = "Doe") {
    // member variables
    var age: Int? = null
    var hobby: String = "watch netflix"

    // initializer block
    init {
        println("Person created with firstName: $firstName and lastName: $lastName")
    }

    constructor(firstName: String, lastName: String, age: Int) : this(firstName, lastName) {
        this.age = age
        println("Person created with firstName: $firstName and lastName: $lastName and age: $age")
    }

    // member functions
    fun displayHobby() {
        println("$firstName hobbie is $hobby")
    }

}

class MobilePhone(osName: String, brand: String, model: String) {
    private var battery: Int = 67

    init {
        println("MobilePhone created with osName: $osName, brand: $brand and model: $model")
    }

    fun chargeBattery(amount: Int) {
        println("Battery was $battery and is now ${battery + amount}")
        battery += amount
    }

}


open class Vehicle {}
open class Car : Vehicle(), Drivable {
    private var range: Double = 0.0
    lateinit var owner: String
    val myBrand: String = "Toyota"
        get() {
            return field.lowercase()
        }

    override var maxSpeed: Double = 200.0
        set(value) {
            field = if (value > 0.0) value else 0.0
        }

    var myModel: String = "Corolla"
        private set

    init {
        owner = "John Doe"
    }

    fun extendRange(amount: Double) {
        if (amount > 0) {
            range += amount
        }
    }

    override fun drive(distance: Double) {
        println("$owner is driving $distance km")
    }
}

class ElectricCar(batteryLife: Double, override var maxSpeed: Double = 150.0) : Car() {
    override fun drive(distance: Double) {
        println("$owner is driving $distance km on electricity")
    }

    override fun brake() {
        super.brake()
        println("Regenerating battery on brakes")
    }
}


interface Drivable {
    val maxSpeed: Double
    fun drive(distance: Double)
    fun brake() {
        println("The driver is braking")
    }
}


data class User(val id: Long, var name: String)
