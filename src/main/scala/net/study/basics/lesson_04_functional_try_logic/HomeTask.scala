package net.study.basics.lesson_04_functional_try_logic

import net.study.basics.lesson_04_functional_try_logic.HomeTask.{TemporaryUnavailableException, fileSource, getDataFromAlternativeSource, getDataFromMainSource, getSubscribers}
import net.study.basics.lesson_04_functional_try_logic.Lesson_04.NetworkException

import scala.io.{BufferedSource}
import scala.io.Source.fromFile
import scala.util.{Failure, Success, Try}

object HomeTask extends App {

  // 1) try to read file from external system over network (use method getFile with two columns: 1) msisdn, subscriber type)
  // and don't forget to close resource after usage!!!!!!

  // 2) try to enrich get Data using main source getDataFromMainSource

  // 3) if fail to execute step 2) go to alternative source and try once more (use getDataFromAlternativeSource)

  // 4) if success to do so, try to send to 3-d party system all list

  // 5) Implement enrichAndSend method with proper Left(Error) type or Right[Int] Quantity of msisdns send to our third party system

  // Conditions:
  // use only Try Monad to resolve all problems with exception handling
  // You can use any additional custom functions/methods
  // Don't use Try monad methods such as get, getOrElse, isSuccess, isFailure !!!!!

  /// ===============help code ======================

  trait Error

  case object NetworkError extends Error // if sftp server not available

  case object SourceTemporaryUnavailableError extends Error // if main source main source unavailable

  case object AllSourceTemporaryUnavailableError extends Error //if all source were unavailable

  case object ThirdPartySystemError extends Error //if 3-d party system error

  case object UnexpectedError extends Error

  case class TemporaryUnavailableException(string: String) extends Exception

  case class ThirdPartySystemException(string: String) extends Exception

  case class SubscriberInfo(msisdn: String, subscriberType: Int, isActive: Boolean)

  val fileSource = "src/main/resources/lesson_04/externalSourceFile.txt"


  // do not change this methods !!!!
  @throws[NetworkException]
  def getFile(isRisky: Boolean, source: String) = if (isRisky) throw NetworkException("SFTP server network exception") else fromFile(source)

  @throws[TemporaryUnavailableException]
  def getActiveData(isRisky: Boolean, msisdns: Seq[String]) = if (isRisky) throw TemporaryUnavailableException("Temporary Unavailable Exception") else {
    msisdns.map(m => (m, if (m.toInt % 2 == 0) 1 else 0))
  }

  @throws[TemporaryUnavailableException]
  def getDataFromMainSource(isRisky: Boolean, msisdns: Seq[String]) = getActiveData(isRisky, msisdns)

  @throws[TemporaryUnavailableException]
  def getDataFromAlternativeSource(isRisky: Boolean, msisdns: Seq[String]) = getActiveData(isRisky, msisdns)

  @throws[ThirdPartySystemException]
  def sendToProvider(isRisky: Boolean, msisdns: Seq[SubscriberInfo]): Unit =
    if (isRisky) throw ThirdPartySystemException("third party system exception") else msisdns.foreach(m => println(s"Sent $m"))

  // ==========================================================

  def getSubscribers(source: String, getFileIsRisky: Boolean): Try[Seq[SubscriberInfo]] = {
    val subs = Try(getFile(getFileIsRisky, source))
    subs match {
      case Failure(e) => Failure(e)
      case Success(v) => Try(dataToSeq(v))
    }
  }

  def getStates(subscribers: Seq[SubscriberInfo], getStatesFromMainIsRisky: Boolean, getStatesFromAltIsRisky: Boolean): Try[Map[String, Int]] = {
    val msisdns = subscribers.map(s => s.msisdn)
    Try(getDataFromMainSource(getStatesFromMainIsRisky, msisdns))
      .orElse(Try(getDataFromAlternativeSource(getStatesFromAltIsRisky, msisdns))).map(s => s.toMap)
  }

  def dataToSeq(data: BufferedSource): Seq[SubscriberInfo] = { // Преобразование BufferedSource в Seq[String]
    val lines = data.getLines() // get the lines
    val arr = lines.map(l => l.split(";"))
    arr.map(a => SubscriberInfo(msisdn = a(0), subscriberType = a(1).toInt, isActive = false)).toSeq
  }

  def enrichSubs(subscribers: Seq[SubscriberInfo], states: Map[String, Int]): Seq[SubscriberInfo] =
    subscribers.map(s => s.copy(isActive = states.get(s.msisdn).map(s => s != 0).getOrElse(false)))

  // implement this one
  def enrichAndSend(getFileIsRisky: Boolean,
                    getDataFromMainSourceIsRisky: Boolean,
                    getDataFromAlternativeSourceIsRisky: Boolean,
                    sendToProviderIsRisky: Boolean,
                    fileSource: String): Either[Error, Unit] = {
    val a = for {
      subs <- getSubscribers(fileSource, getFileIsRisky)
      states <- getStates(subs, getDataFromMainSourceIsRisky, getDataFromAlternativeSourceIsRisky)
      eSubs = enrichSubs(subs, states)
//            _ = subs.foreach(println)
//            _ = states.foreach(println)
//            _ = eSubs.foreach(println)
      result <- Try(sendToProvider(sendToProviderIsRisky, eSubs))
    } yield result

    a.toEither.left.map {
      case _: NetworkException => NetworkError
      case _: TemporaryUnavailableException => AllSourceTemporaryUnavailableError
      case _: ThirdPartySystemException => ThirdPartySystemError
      case e: Throwable => println(e); UnexpectedError
    }
  }

  val result = enrichAndSend(false, false, false, false, fileSource)
  println(result)
}
