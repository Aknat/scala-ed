package net.study.basics.lesson_07_functions

object Lesson_07 extends App {

  // partial applied function

  def sum(a: Int, b: Int): Int = a + b

  val sumFunc: (Int, Int) => Int = sum _

  val r1: Int = sumFunc(1,2)


  // partial parameter assignment

  val partialSumApply = sumFunc(1, _:Int)

  val r2: Int = partialSumApply(2)


  // currying

  def sumTriple(a: Int, b: Int, c: Int): Int = a + b + c

  val sumTripleFunc: (Int, Int, Int) => Int = sumTriple _

  val sumTripleCurrFunc = (sumTriple _).curried

}
