package net.study.add

object lesson_03_functions extends App {

  //  Seq(Option("apple"),Option("cherry"),None,Option("beer"), Option("mango"), None, None, Option("wine"))
  //    .map{
  //      case a if a.nonEmpty => a.get.length
  //      case b if b.isEmpty => 0
  //    }
  //    .foreach(println)

  //  Seq(Option("apple"), Option("cherry"), None, Option("beer"), Option("mango"), None, None, Option("wine"))
  //    .map(sOpt => sOpt.map(s => s.length).getOrElse(0))
  //    .foreach(println)


  //  Seq(Option("apple"), Option("cherry"), None, Option("beer"), Option("mango"), None, None, Option("wine"))
  //    .map {
  //      case a if a.nonEmpty && a.get == "apple" => 1
  //      case a if a.nonEmpty && a.get == "cherry" => 2
  //      case a if a.nonEmpty && a.get == "mango" => 3
  //      case b if b.nonEmpty && b.get == "beer" => 4
  //      case b if b.nonEmpty && b.get == "wine" => 5
  //      case _ => 0
  //    }
  //    .foreach(println)

  //  Seq(Option("apple"), Option("cherry"), None, Option("beer"), Option("mango"), None, None, Option("wine"))
  //    .map {
  //      case a if a.contains("apple") => 1
  //      case a if a.contains("cherry") => 2
  //      case a if a.contains("mango") => 3
  //      case b if b.contains("beer") => 4
  //      case b if b.contains("wine") => 5
  //      case _ => 0
  //    }
  //    .foreach(println)

  //  Seq(Option("apple"), Option("cherry"), None, Option("beer"), Option("mango"), None, None, Option("wine"))
  //    .map {
  //      case Some("apple") => 1
  //      case Some("cherry") => 2
  //      case Some("mango") => 3
  //      case Some("beer") => 4
  //      case Some("wine") => 5
  //      case None => 0
  //    }
  //    .foreach(println)

  //  Seq("apple", "cherry", "cherry", "mango", "apple")
  //    .groupBy(t => t)
  //    .map(t => (t._1, t._2.size))
  //    .foreach(println)

//  Seq("apple", "cherry", "cherry", "mango", "apple")
//    .groupBy(t => t)
//    .map(t => (t._1, t._2.size))
//    .filter(a => a._2 > 1)
//    .foreach(println)


//  Seq("apple", "cherry", "cherry", "mango", "apple")
//    .groupBy(t => t)
//    .map(t => (t._1, t._2.size))
//    .filter(a => a._2 > 1)
//    .foreach(println)

  Seq("apple", "cherry", "cherry", "mango", "apple").collect {
    case "apple" => 0
    case "cherry" => 1
  }
    .foreach(println)
}
