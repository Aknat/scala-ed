package net.study.basics.lesson_03_cases_class

object Lesson_03_02_options {

  // Monad - a container with its inner context, which always has 2 methods: 'flatMap', 'unite'
  // a shreddinger monad) --> you never know if it has smth or not

  // 'Option' monad --> is used to  work with 'possible' context (either it exists or not)
  // 'Option' has 2 states: 'Some', 'None'
  // 'Option' is a subtype for the 'Some', 'None'

  val maybeInt1 = Option(1)
  val maybeInt2 = None

  val result = maybeInt1 match {
    case Some(a) => a
    case None => 0
  }

}
