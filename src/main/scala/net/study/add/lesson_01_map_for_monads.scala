package net.study.add

import scala.util.{Failure, Random, Success, Try}

object lesson_01_map_for_monads {
  def main(args: Array[String]): Unit = {

    val opt = Option.empty[Int]
    val a = opt.map(a => a * 2)
    println(a)

    val cities = Seq("Praha", "Kyiv","Kherson", "Berlin")

    def getPopulation(city: String): Option[Int] = {
      val populations = Map("Kyiv" -> 42, "Praha" -> 666, "Kherson" -> 333)
      populations.get(city)
    }

    val pop1 = cities.map(c => getPopulation(c))
    println(pop1)

    val pop2 = cities.map(c => getPopulation(c))
//    val pop3 = pop2.filter(c => c.isDefined)
//    val pop3 = pop2.flatten
    val pop3 = cities.flatMap(c => getPopulation(c))
    println(pop3)


    val t1 = Try[Int](throw new RuntimeException("aaa!!!!!"))
//    val t2 = Try(123)
//    val t4 = t.flatMap(x => x * 2)
    val t = Seq(t1).filter(t => t.isSuccess)
    println(t.headOption)

    def tryToGet(): Either[Throwable, Int] =
      if (Random.nextBoolean())
        Right(123)
      else
        Left(new RuntimeException("aa!!!!"))

     tryToGet().map(e => e + 100)
    println(tryToGet().map(e => e + 100))
  }
}
