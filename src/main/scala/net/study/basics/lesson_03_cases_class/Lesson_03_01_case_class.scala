package net.study.basics.lesson_03_cases_class

object Lesson_03_01_case_class extends App {

  // when we create 'case class' then 2 entities are created: 1 - Class, 2 - companion Object with tech methods
  // case classes are immutable
  // case classes are not used for the business logic (mostly)
  // can have default values set as follows:
        // case class Person(name: String, age: Int = 666, isMarried: Boolean = true)
              // TIP:
              // do not use a lot of defaults
              // do not use defaults with the same type
              // try to put them as last params

  case class Person(name: String, age: Int) // all params are final - 'val'

  val personA = Person("Sasha", 20) // val personA = Person.apply("Sasha", 20)  -> companion Object tech method 'apply'
  val personB = Person("Sasha", 20)


  val personC = personB.copy(age = 33) // to change 1 param --> make a shallow copy of existing Object


  println(personA == personB) // objects are compared - true
  println(personA equals personB) // objects are compared - true
  println(personA eq personB) // links are compared   - false
  println(personA eq personA) // links are compared   - true


  def analyze(person: Person): String = person match {
    case Person(name, 20) => s"name=$name, age=20"
    case Person("Ivan", age) => s"name=Ivan, age=$age"
    case a => s"another person = $a" // mandatory condition; cannot be omitted
  }

  val personD = Person("Petro", 20)
  val personE = Person("Ivan", 48)
  val personF = Person("Ivan", 69)

  println(analyze(personD))
  println(analyze(personE))
  println(analyze(personF))

}
