package net.study.basics.lesson_03_cases_class

import net.study.basics.lesson_02_for_expressions.Lesson_02.seqInt

object HomeTask extends App {

  // if sum not submitted, precise in payment service. In case not found remove from final report.
  // if tax sum is not assigned calculate it(20%) (minimal tax free sum for calculation = 100 or reversal payments are tax free)
  // if desc is empty default value will be "technical"
  // duplicates must be removed
  // get method is not allowed, use for comprehension in main calculation method
  // output must be type of Seq[PaymentInfo] !!!!!!!!!

  final case class PaymentInfoDto(paymentId: Int, customer: Option[String], sum: Option[Long], tax: Option[Long], desc: Option[String])

  final case class PaymentInfo(paymentId: Int, sum: Long, tax: Long, desc: String)

  object PaymentCenter {
    def getPaymentSum(id: Int): Option[Long] = Option(id) filter (_ > 2) map (_ * 100)

    def getTax(sum: Long): Long = sum match {
      case a if sum > 100 => (sum / 100) * 20
      case _ => 0
    }
  }

  val payments = Seq(
    PaymentInfoDto(1, Some("customerA"), Some(1500), None, Some("payment for Iphone 15")),
    PaymentInfoDto(2, Some("customerH"), None, None, Some("technical payment")),
    PaymentInfoDto(3, Some("customerB"), Some(99), None, Some("payment for headphone")),
    PaymentInfoDto(4, Some("customerC"), Some(1000), Some(200), None),
    PaymentInfoDto(5, Some("customerD"), Some(2500), None, None),
    PaymentInfoDto(6, Some("customerE"), Some(600), Some(120), Some("payment for Oculus quest 2")),
    PaymentInfoDto(7, Some("customerF"), Some(1500), None, Some("payment for Iphone 15")),
    PaymentInfoDto(8, Some("customerG"), Some(-400), None, Some("roll back transaction")),
    PaymentInfoDto(9, Some("customerH"), None, None, Some("some payment")),
    PaymentInfoDto(4, Some("customerC"), Some(1000), Some(200), None)
  )

  //  val x = payments.map(p => enrichSum(p))
  //  val x = payments.map(p => p.copy(tax = p.sum.map(s => PaymentCenter.getTax(s))))
  //  val x = payments.map(p => enrichDesc(p))

  val x = payments.distinct
    .map(p => enrichSum(p))
    .map(p => p.copy(tax = p.sum.map(s => PaymentCenter.getTax(s))))
    .map(p => enrichDesc(p))
    .flatMap(p => toInfo(p))

  x.foreach(println)


  def toInfo(payment:PaymentInfoDto): Option[PaymentInfo] =
    payment.sum match {
      case Some(sum) => Some(PaymentInfo(
        paymentId = payment.paymentId,
        sum = sum,
        tax = payment.tax.getOrElse(0L),
        desc = payment.desc.getOrElse("None")
      ))
      case None => None
    }


  def enrichSum(payment: PaymentInfoDto): PaymentInfoDto =
    payment.sum match {
      case Some(_) => payment
      case None =>
        val newSum = PaymentCenter.getPaymentSum(payment.paymentId)
        payment.copy(sum = newSum)
    }


  def enrichDesc(payment: PaymentInfoDto): PaymentInfoDto =
    payment.desc match {
      case Some(_) => payment
      case None => payment.copy(desc = Option("technical"))
    }

  val f = (x: Int) => x * 2
  val a = Seq(1, 2, 3, 4).map(x => x * 2)
  //  a.foreach(println)
  //    .map(p => {println(p); p}) // debug
  //  for (n <- x) {
  //    println(n)
  //  }

}
