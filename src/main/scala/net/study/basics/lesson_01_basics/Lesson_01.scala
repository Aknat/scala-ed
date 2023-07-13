package net.study.basics.lesson_01_basics

object Lesson_01 {

  def main(args: Array[String]): Unit = {

//    val a = "Hello Universe" // final; constant
//    var b = "Hello Magic" // variable; very rare used


    def sum(int: Int, int2: Int) = int + int2
    // method signature --> name(inParamName1: inParamType1, inParamName2: inParamType2): outType = computation

    println(sum(2, 6))

    val range = 1 to 10

    def square(x: Int) = x * x

    println(square(square(2)))

    val nums = Seq(1,2,3,5,7)
    for (n <- nums) println(n)

  }
}
