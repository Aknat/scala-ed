package net.study.basics.lesson_08_partial_function

object Lesson_08 extends App {

  // A partial function is a function that does not provide an answer for every possible input value it can be given.
  // It provides an answer only for a subset of possible data, and defines the data it can handle.
  // Can also be queried to determine if it can handle a particular value.

  // only 2 inbound params

  val divisionBasic = new PartialFunction[Int, Int] {
    override def isDefinedAt(x: Int): Boolean = x != 0

    override def apply(v1: Int): Int = 100 / v1
  }

  //  println(divisionBasic(2))
  //  println(divisionBasic(0))

  val divisionNonZero: PartialFunction[Int, Either[Int, Int]] = {
    case x if x != 0 => Right(100 / x)
  }

  val alternative: PartialFunction[Int, Either[Int, Int]] = {
    case _ => Left(0)
  }

  val thenFunction: PartialFunction[Either[Int, Int], Option[Int]] = {
    case x => x.toOption
  }

  val divisionFinal: PartialFunction[Int, Option[Int]] = (divisionNonZero orElse alternative) andThen thenFunction

  println(divisionFinal(0))


}
