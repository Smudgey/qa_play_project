package models

import scala.collection.mutable.ArrayBuffer

/**
  * this class will represent customers credit/debit card details
  *
  * @param uid             User ID
  * @param holder          Card holders name
  * @param cardNumber      Long card number
  * @param cv              CV verification code
  * @param expirationMonth Card expiration month
  * @param expirationYear  Card expiration month
  */
case class CardDetails(uid: String, holder: String, cardNumber: String, cv: String, expirationMonth: String, expirationYear: String) {}

object CardDetails {

  private var cardList = ArrayBuffer[CardDetails](
    CardDetails("000", "a", "0000", "000", "00", "0000")
  )

  /**
    * this function will add customer card details to storage
    *
    * @param uid             User ID
    * @param holder          Card holders name
    * @param cardNumber      Long card number
    * @param cv              CV verification code
    * @param expirationMonth Card expiration month
    * @param expirationYear  Card expiration month
    */
  def addCard(uid: String, holder: String, cardNumber: String, cv: String, expirationMonth: String, expirationYear: String): Unit = {
    cardList += CardDetails(uid, holder, cardNumber, cv, expirationMonth, expirationYear)
  }

  /**
    * this function will get card details using User ID
    *
    * @param uid User ID
    * @return CardDetails object
    */
  def getCard(uid: String): ArrayBuffer[CardDetails] = cardList.filter(_.uid == uid)

  def removeCard(cardNumber: String): Unit = cardList.remove(cardList.indexOf(cardList.find(_.cardNumber == cardNumber).get))
}
