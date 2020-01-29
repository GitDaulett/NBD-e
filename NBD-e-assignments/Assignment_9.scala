

object Assignment_9 {
  def main(args: Array[String]): Unit = {
    println("Task_1:")
    val Task_1: Task_1[String] = new Task_1("Salute!")
    Task_1.applyFunction(a => a + a)
    println(Task_1.getContent)

    println("Task_2:")
    var no: No = new No()
    println(no.isInstanceOf[Maybe[_]])
    var yes: Yes[String] = new Yes("a")
    println(yes.isInstanceOf[Maybe[_]])

    println("Task_3:")
    val Task_3no: Task_3[No] = new Task_3[No](new No())
    Task_3no.applyFunction(a => a)
    println(Task_3no.getContent)
    val Task_3yes: Task_3[Yes[String]] = new Task_3[Yes[String]](new Yes("Salute!"))
    Task_3yes.applyFunction(a => new Yes(a.getContent + a.getContent))
    println(Task_3yes.getContent.getContent)

    println("Task_4:")
    val Task_4no: Task_4[No] = new Task_4[No](new No())
    println(Task_4no.getOrElse)
    val Task_4yes: Task_4[Yes[String]] = new Task_4[Yes[String]](new Yes("Salute!"))
    println(Task_4yes.getOrElse)

  }
}
class Task_1[A](c: A) {
  private var _c: A = c
  def getContent: A = _c
  def applyFunction(f: A => A): A =
    {
      _c = f(_c)
      return _c
    }
}

trait Maybe[A]
class No extends Maybe[Nothing]
class Yes[A](value: A) extends Maybe[A] {
  private var v: A = value
  def getContent: A = v
}

class Task_3[A](c: A) {
  private var _c: A = c
  def getContent: A = _c

  def applyFunction(f: A => A): A =
    {
      if (f(_c).isInstanceOf[No])
        return _c
      else if (f(_c).isInstanceOf[Yes[_]]) {
        _c = f(_c).asInstanceOf[A]
        return _c
      } else
        return null.asInstanceOf[A];
    }
}

class Task_4[A](c: A) {
  private var _c: A = c
  def getContent: A = _c
  def getOrElse[B]: B =
    {
      if (_c.isInstanceOf[No])
        return "This is class NO with no content".asInstanceOf[B]
      else if (_c.isInstanceOf[Yes[_]])
        return _c.asInstanceOf[Yes[A]].getContent.asInstanceOf[B]
      else
        return null.asInstanceOf[B];
    }
}
