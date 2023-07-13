package net.study.basics.lesson_02_for_expressions

object Lesson_02 extends App {

  val seqInt = Seq(1, 2, 3, 4, 5)

  seqInt.foreach(println)

  for (n <- seqInt) {
    println(n)
  }

  val result1 = seqInt.map(x => x * x)
  println(result1)

  val result2 = for {n <- seqInt} n * n

}
