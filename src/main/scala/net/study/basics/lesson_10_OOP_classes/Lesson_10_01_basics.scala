package net.study.basics.lesson_10_OOP_classes

import net.study.basics.lesson_10_OOP_classes.Person.isMarriedDefined

object Lesson_10_01_basics extends App {

  val person1 = new Person("Anakin", 18, false)
  val person2 = new Person("Padme", 18)

  person1.name = "Vader"
  //  person1.age = 28                       // impossible, not compiling
}


class Person(var name: String, val age: Int, isMarried: Boolean) {
  def this(name: String, age: Int) {
    this(name, age, isMarriedDefined(age))
  }

  def this() {
    this(name, age, isMarriedDefined(age))
  }
}

object Person {
  private def isMarriedDefined(age: Int) = age > 18

  def apply(name: String, age: Int): Person = new Person(name, age, true)
  def apply(name: String, age: Int, isMarried: Boolean): Person = new Person(name, age, isMarried)
}