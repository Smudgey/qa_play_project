package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Luke on 08/07/2016.
  */
case class Payment(cardHolderName: String, cardNumber: String, cv: Int, expirationMonth: String, expirationYear: String) {}

object Payment {
  //Dummy data
  private var list = ArrayBuffer[Payment](
    Payment("Luke Smith", "1234567891123456", 123, "10", "2017"),
    Payment("Rytis Jazdauskas", "6543211987654321", 321, "12", "2016"),
    Payment("Emma Upton", "4637285647389957", 258, "05", "2016"),
    Payment("Mark Kelly", "9485777685645321", 789, "02", "2017")
  )

  def setPaymentMethod(payment: Payment): Unit ={

  }

  /* Create a new payment */
  def createPayment(cardHolderName: String, cardNumber: String, cv: Int, expirationMonth: String, expirationYear: String): Boolean = {
    if(findCardNumber(cardNumber).isEmpty) {
      //Failed to find existing payment - create new
      list += Payment(cardHolderName, cardNumber, cv, expirationMonth, expirationYear)
      true //success
    } else {
      //Find an existing payment
      println("Card already exists")
      false //failure
    }
  }

  /* Find an existing payment */
  def findCardNumber(cardNumber: String) = list.find(_.cardNumber == cardNumber)
}
