object Assignment_2 {
  val days = List("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
  val products = Map("fruits" -> 1000, "vegetables" -> 500)
  def main(args: Array[String]): Unit = {
    println("Task_1:")
    println(Task_1("monday"))
    println(Task_1("saturday"))
    println(Task_1("8th day"))
    println("Task_2:")
    val b: BankAccount = new BankAccount()
    b.currentBalance
    b.deposits(20.3)
    b.currentBalance
    b.withdraw(10)
    b.currentBalance
    new BankAccount(1000).currentBalance
    println("Task_3:")
    val adam: PersonTask_3 = new PersonTask_3("Adam", "Sandler")
    adam.greeting(new PersonTask_3("Bakdaulet", "Tabynbaev"))
    adam.greeting(new PersonTask_3("Harley", "Davidson"))
    adam.greeting(new PersonTask_3("Bradley", "Cooper"))
    println("Task_4:")
    println(Task_4(x => x * x, 2))
    println("Task_5:")
    object p1 extends PersonTask_5("Bakdaulet", "Tabynbaev") with Employee
    p1.salary_(1000)
    p1.taxToPay
    object p2 extends PersonTask_5("Bakdaulet", "Tabynbaev") with Student
    p2.taxToPay
    object p3 extends PersonTask_5("Bakdaulet", "Tabynbaev") with Teacher
    p3.salary_(1000)
    p3.taxToPay
    object p4 extends PersonTask_5("Bakdaulet", "Tabynbaev") with Employee with Student
    p4.salary_(1000)
    p4.taxToPay
    object p5 extends PersonTask_5("Bakdaulet", "Tabynbaev") with Student with Employee
    p5.salary_(1000)
    p5.taxToPay
  }
  def Task_1(day: String): String = day match {
    case d if days.map(_.toLowerCase()).filter(!_.startsWith("s")).contains(d.toLowerCase()) => return "work"
    case d if days.map(_.toLowerCase()).filter(_.startsWith("s")).contains(d.toLowerCase()) => return "weekends"
    case _ => return "no such day"
  }

  def Task_4(f: Int => Int, a: Int): Int = f(f(f(a)))

}

class BankAccount(private var balance: Double) {

  def this() { this(0) }

  def deposits(amount: Double): Unit = {
    if (amount > 0) balance = balance + amount
    else throw new Exception("incorrect input")
  }

  def withdraw(amount: Double): Unit = {
    if (0 < amount && amount <= balance) {
      balance = balance - amount
    } else throw new Exception("incorrect input")
  }

  def currentBalance: Unit = println("Current balance: " + balance)
}

case class PersonTask_3(var firstName: String, var lastName: String) {
  def greeting(person: PersonTask_3): Unit = person match {
    case PersonTask_3("Harley", _) => println("Hi! Harley")
    case PersonTask_3(_, "Tabynbaev") => println("Hi! Mr. Tabynbaev")
    case PersonTask_3(fn, ln)     => println(s"Hello! $fn $ln")
  }
}

abstract class PersonTask_5(private var firstName: String, private var lastName: String) {
  def taxToPay: Unit
}
trait Employee extends PersonTask_5 {
  private var sal: Double = _
  def salary = sal
  def salary_(s: Double): Unit = sal = s
  override def taxToPay: Unit = println("Paying tax: " + sal * 0.2)
}
trait Student extends PersonTask_5 {
  override def taxToPay: Unit = println("Paying tax: 0")
}
trait Teacher extends Employee {
  override def taxToPay: Unit = println("Paying tax: " + salary * 0.1)
}

