package net.study.basics.lesson_04_functional_try_logic

import net.study.basics.lesson_04_functional_try_logic.HomeTask.{NetworkError, SubscriberInfo}
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

import scala.io.Source
//import org.scalatest.junit.JUnitRunner
import org.scalatest.funsuite.AnyFunSuite

@RunWith(classOf[JUnitRunner])
class HomeTaskTest extends AnyFunSuite {

  val source = "src/test/scala/net/study/basics/lesson_04_functional_try_logic/externalSourceFile.txt"

  test("testGetFile") {
    val isNotRisky = false
    val isRisky = true

    val dataSuccess = HomeTask.getFile(isNotRisky,source)
    assert(dataSuccess.nonEmpty)

//    How to implement assertion for the Exception?
//    val dataFail = HomeTask.getFile(isRisky,source)
//    assertThrows(NetworkError)
  }

  test ("testGetActiveData") {
    val isNotRisky = false
    val msisdns = Seq("0672330769", "0672330768", "0672330767")

    val subscribers = HomeTask.getActiveData(isNotRisky,msisdns)
    assert(subscribers.nonEmpty)
    assert(subscribers(0)._2 == 0)
    assert(subscribers(1)._2 == 1)
    assert(subscribers(2)._2 == 0)
  }

  test("testGetSubscribers") {
    val subscribers = HomeTask.getSubscribers(source, false)
    assert(subscribers.isSuccess)

    val subs = subscribers.get
    assert(subs.nonEmpty)

    val sub = subs.head
    val msisdn = sub.msisdn
    assert(msisdn.matches("[\\d]{10}"))
    //    subscribers.foreach(_.foreach(println))
  }

  test("testGetStates") {
    val inputSubs = Seq(
      SubscriberInfo("0672330769", 0, false),
      SubscriberInfo("0672330768", 0, false),
      SubscriberInfo("0672330767", 0, true))

    val subscribers = HomeTask.getStates(subscribers = inputSubs, getStatesFromMainIsRisky = false, getStatesFromAltIsRisky = false)
    assert(subscribers.isSuccess)

    val subs = subscribers.get
    assert(subs.nonEmpty)
    assert(subs("0672330769") == 0)
    assert(subs("0672330768") == 1)
    assert(subs("0672330767") == 0)
  }

  test("testDataToSeq") {
    val data = Source.fromFile(source)
    val subscribers = HomeTask.dataToSeq(data)
    assert(subscribers.nonEmpty)

    val sub = subscribers.head
    assert(sub.msisdn == "0673052785")
    assert(sub.subscriberType == 1)
    assert(!sub.isActive)

    subscribers.foreach(s => assert(s.msisdn.nonEmpty))
  }

  test("testEnrichSubs") {
    val inputSubs = Seq(
      SubscriberInfo("0672330769", 0, false),
      SubscriberInfo("0672330768", 1, false),
      SubscriberInfo("0672330767", 0, false),
      SubscriberInfo("0672330780", 0, true))

    val inputStates = Map(
      "0672330769" -> 1,
      "0672330768" -> 0,
      "0672330767" -> 1)

    val subscribers = HomeTask.enrichSubs(inputSubs, inputStates)
    assert(subscribers.nonEmpty)
    assert(subscribers(0).isActive)
    assert(!subscribers(1).isActive)
    assert(subscribers(2).isActive)
    assert(!subscribers(3).isActive)
  }

  test("testEnrichAndSend") {
    val getFileIsNonRisky = false
    val getFileIsRisky = true
    val getDataFromMainSourceIsRisky = false
    val getDataFromAlternativeSourceIsRisky = false
    val sendToProviderIsRisky = false
    val fileSource = source

    val resultPositive = HomeTask.enrichAndSend(
      getFileIsNonRisky,
      getDataFromMainSourceIsRisky,
      getDataFromAlternativeSourceIsRisky,
      sendToProviderIsRisky,
      fileSource)

    assert(resultPositive.isRight)

    val resultNegative = HomeTask.enrichAndSend(
      getFileIsRisky,
      getDataFromMainSourceIsRisky,
      getDataFromAlternativeSourceIsRisky,
      sendToProviderIsRisky,
      fileSource)

    assert(resultNegative.isLeft)
  }
}
