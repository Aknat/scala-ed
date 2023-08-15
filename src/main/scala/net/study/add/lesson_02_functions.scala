package net.study.add

object lesson_02_functions extends App {

  val f = (a: String) => {
    a.length
  }

  //  Seq("hello", "word", "english").map(f).foreach(println)
  //  Seq("hello", "word", "english").map(s => f(s)).foreach(println)
  //  Seq("hello", "word", "english").map(s => f(s)).foreach(s => println(s))

  val fSmallA = (str: String) => {
    str.toSeq.count(_.equals('a'))
  }

  //  Seq("apple", "bananas", "apricot", "aaaaa").map(fSmallA).foreach(println)

  val groupStrings = (str: String) => {
  str match {
      case a if a.length == 0 => 0
      case b if b.length <= 5 => 1
      case c if 5 < c.length && c.length <= 10 => 2
      case d if d.length > 10 => 3
      case _ => 100
    }
  }

  Seq("apple", "bananas", "apricot", "aaaaa", "", "12345678901")
    .map {
      case a if a.length == 0 => 0
      case b if b.length <= 5 => 1
      case c if 5 < c.length && c.length <= 10 => 2
      case d if d.length > 10 => 3
      case _ => 100
    }
    .foreach(println)
}
