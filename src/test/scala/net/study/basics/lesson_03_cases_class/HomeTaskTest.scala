package net.study.basics.lesson_03_cases_class

import net.study.basics.lesson_03_cases_class.HomeTask.{PaymentInfoDto, toInfo, enrichSum, enrichDesc}
import net.study.basics.lesson_03_cases_class.HomeTask.PaymentCenter.{getPaymentSum, getTax}
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.funsuite.AnyFunSuite

@RunWith(classOf[JUnitRunner])
class HomeTaskTest extends AnyFunSuite {

  test("testGetPaymentSum") {
    assert(getPaymentSum(2).isEmpty)
    assert(getPaymentSum(3).contains(300))
    assert(getPaymentSum(4).contains(400))
  }

  test("testGetTax") {
    assert(getTax(99) == 0)
    assert(getTax(100) == 0)
    assert(getTax(101) == 20)
    assert(getTax(200) == 40)
  }

  test("testToInfo") {
    val dtoSuccess = PaymentInfoDto(1, Some("customerA"), Some(1500), Some(300), Some("payment for Iphone 15"))
    val dtoFail = PaymentInfoDto(2, Some("customerB"), None, None, Some("technical payment"))
    assert(toInfo(dtoSuccess).nonEmpty)
    assert(toInfo(dtoFail).isEmpty)
  }

  test("testEnrichSum") {
    val dtoWithSum = PaymentInfoDto(1, Some("customerA"), Some(1500), Some(300), Some("payment for Iphone 15"))
    val dtoWithoutSumFail = PaymentInfoDto(2, Some("customerB"), None, None, Some("technical payment"))
    val dtoWithoutSumSuccess = PaymentInfoDto(3, Some("customerC"), None, None, Some("technical payment"))

    assert(enrichSum(dtoWithSum).sum.contains(1500))
    assert(enrichSum(dtoWithoutSumFail).sum.isEmpty)

    val noSumSuccess = enrichSum(dtoWithoutSumSuccess).sum
    assert(noSumSuccess.contains(dtoWithoutSumSuccess.paymentId * 100))
  }

  test("testEnrichDesc") {
    val dtoWithDesc = PaymentInfoDto(1, Some("customerA"), None, None, Some("some test descr"))
    val dtoWithoutDesc = PaymentInfoDto(2, Some("customerB"), None, None, None)

    assert(enrichDesc(dtoWithDesc).desc.contains("some test descr"))
    assert(enrichDesc(dtoWithoutDesc).desc.contains("technical"))
  }
}
