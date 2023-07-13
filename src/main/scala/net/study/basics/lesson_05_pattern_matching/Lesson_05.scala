package net.study.basics.lesson_05_pattern_matching

object Lesson_05 extends App {

  //  SIMPLE CONSTANT pattern matching

  val intSeq: Seq[Int] = 1 to 10
  val maybeInt: AnyVal = true

  maybeInt match {
    case 1 => println(1)
    case 10 => println(10)
    case _ => println("other")
  }

  //  CASE CLASS pattern matching

  case class Person(name: String, age: Int)

  val personA: Any = Person("Max", 33)

  personA match {
    case Person(nam, age) =>
    case _ =>
  }

  //  TUPLE pattern matching

  val simpleTuple: (Int, String) = (1, "hello")

  simpleTuple match {
    case (myInt, myString) =>
    case _ =>
  }

  //  SEQ pattern matching

  intSeq match {
    case List(1, _*) => println("head")
  }
}
