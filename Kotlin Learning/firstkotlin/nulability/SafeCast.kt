package nulability

fun main(args: Array<String>) {
    val human:Human = Human("Hazem",22)
    val unKnown = human as?Employee?:Cashier("Hazem",23,1)
    println("${human}")
    println(unKnown is Cashier)

}
open class Human(val name:String, val age:Int)
class Employee(name: String, age: Int,val empId:Int): Human(name, age)
class Cashier(name: String, age: Int,val cashierId:Int): Human(name, age)
