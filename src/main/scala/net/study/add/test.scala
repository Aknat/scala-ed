package net.study.add

object test {

  def main(args: Array[String]) {
    println(io.Source.stdin.getLines().take(3).map(_.toInt).sum)
  }
}
