package net.study.basics.lesson_07_functions

import scala.annotation.tailrec

object Lesson_07 extends App {

  type LibraryComputationType = Int => Int

  // PARTIAL APPLIED FUNCTION         --> a mechanism that allows to:

  // ignore some missing parameters
  // to prolong the computation by dividing computation into several steps

  def sum(a: Int, b: Int): Int = a + b // method - as a generator/fabric of the function

  1 // all parameters are unavailable
  val sumFunc: (Int, Int) => Int = sum _
  val r1: Int = sumFunc(1, 2)


  2 // partial parameter assignment
  val partialSumApply = sumFunc(1, _: Int)
  val r2: Int = partialSumApply(2)

  def adder(d: Int, func: LibraryComputationType): Int = func(d * 2)

  println(adder(5, partialSumApply))


  // CURRYING - is the process of converting a function with multiple arguments into a sequence of functions that take one argument.
  // Each function returns another function that consumes the following argument

  def sumTriple(a: Int, b: Int, c: Int): Int = a + b + c

  val sumTripleFunc: (Int, Int, Int) => Int = sumTriple _

  val sumTripleCurrFunc: Int => Int => Int => Int = (sumTriple _).curried

  sumTripleCurrFunc(0)(1)(2)

  val another2: Int => Int => Int = sumTripleCurrFunc(0)

  val another3: Int => Int = another2(1)

  val resultOfCurring: Int = another3(5)

  val uncurriedFunc = Function.uncurried(another2)


  // HIGH ORDER FUNCTION
  //  accept 1 or more functions to the parameter List AND/OR provides back the same function

  def biFunction(a: Int, b: Int, computationFunc: (Int, Int) => Int) = {
    // some computation
    println((a, b))
    computationFunc(a, b)
  }

  val partialFunctionTripleFunc: (Int, Int) => Int = sumTriple(10, _: Int, _: Int)

  val sumResult = biFunction(1, 2, _ + _)

  val division = biFunction(1, 2, _ / _)

  val multiplying = biFunction(1, 2, _ * _)

  val anotherResult = biFunction(1, 2, partialFunctionTripleFunc)

  ////  RECURSION

  // outer function/ method
  def factorial(number: Int): Int = {

    // inner function/method
    @tailrec
    def factorialCompute(n: Int, acc: Int = 1): Int = {
      if (n <= 1) acc else factorialCompute(n - 1, acc * n)
    }
    factorialCompute(number)
  }

  println(factorial(100))

}
