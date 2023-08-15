package net.study.add

object lesson_04_partial_function extends App {

  val smallLengthProcessing = new PartialFunction[String, Int] {
    override def isDefinedAt(x: String): Boolean = x.length <= 3
    override def apply(v1: String): Int = v1.length
  }

  val longLengthProcessing = new PartialFunction[String, Int] {
    override def isDefinedAt(x: String): Boolean = x.length > 3
    override def apply(v1: String): Int = v1.length * 10
  }

  Seq("jupiter", "mars", "venus", "earth", "aa")
    .map(longLengthProcessing orElse smallLengthProcessing)
    .foreach(println)
}
