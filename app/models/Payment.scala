package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Luke on 08/07/2016.
  */
case class Payment(cardHolderName: String, cardNumber: String, cv: Int, expirationMonth: String, expirationYear: String) {}

object Payment {
  //Dummy data
  private var list = ArrayBuffer[Payment]{
    Payment("Mr L Smith", "1234567891123456", 123, "10", "2017"),
    Payment("Mr R Jazdauskas", "6543211987654321", 321, "12", "2016"),
    Payment("Ms E Upton", "4637285647389957", 258, "05", "2016"),
    Payment("Mr M Kelly", "9485777685645321", 789, "02", "2017")
  }

  /*  */
}
