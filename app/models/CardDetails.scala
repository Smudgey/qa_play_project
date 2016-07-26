package models

import scala.collection.mutable.ArrayBuffer

/**
  * this class will represent customers credit/debit card details
  *
  * @param cardID          User ID
  * @param holder          Card holders name
  * @param cardNumber      Long card number
  * @param cv              CV verification code
  * @param expirationMonth Card expiration month
  * @param expirationYear  Card expiration month
  */
case class CardDetails(cardID: String, holder: String, cardNumber: String, cv: String, expirationMonth: String, expirationYear: String) {}

case class CardMap(accountID: String, cardID: String) {}

object CardDetails extends Formatter {


  private var cardMap = ArrayBuffer[CardMap](
    CardMap("0", "c0"),
    CardMap("0", "c1")
  )


  private var cardList = ArrayBuffer[CardDetails](
    CardDetails("c0", "a", "0000", "000", "00", "0000"),
    CardDetails("c1", "b", "1111", "111", "11", "1111")
  )

  /**
    * this function will add customer card details to storage
    *
    * @param accountID       Account ID
    * @param cardID          User ID
    * @param holder          Card holders name
    * @param cardNumber      Long card number
    * @param cv              CV verification code
    * @param expirationMonth Card expiration month
    * @param expirationYear  Card expiration month
    */
  def addCard(accountID: String, cardID: String, holder: String, cardNumber: String, cv: String, expirationMonth: String, expirationYear: String): Unit = {
    cardList += CardDetails(cardID, holder, cardNumber, cv, expirationMonth, expirationYear)
    cardMap += CardMap(accountID, cardID)
  }

  /**
    * this function will get card details using User ID
    *
    * @param accountID User ID
    * @return CardDetails object
    */
  def getCards(accountID: String): ArrayBuffer[CardDetails] = {
    val cardIDs = cardMap.filter(_.accountID == accountID)
    var toReturn = ArrayBuffer[CardDetails]()
    for (card <- cardIDs) {
      toReturn += cardList.find(_.cardID == card.cardID).get
    }
    toReturn
  }

  def removeCard(cardNumber: String): Unit = {
    val card = cardList.find(_.cardNumber == cardNumber).get
    cardList.remove(cardList.indexOf(card))
    cardMap.remove(cardMap.indexOf(cardMap.find(_.cardID == card.cardID).get))

  }
}
