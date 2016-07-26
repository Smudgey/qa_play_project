package models

import scala.collection.mutable.ArrayBuffer

/*
  * Create By rytis
  *
  * Last worked on by Rytis on 26/07/2916
  */

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
    CardMap("c0", "0"),
    CardMap("c0", "1")
  )

  private var cardList = ArrayBuffer[CardDetails](
    CardDetails("0", "a", "0000", "000", "00", "0000"),
    CardDetails("1", "b", "1111", "111", "11", "1111")
  )

  /**
    * this function will add customer card details to storage
    *
    * @param accountID       Account ID
    * @param holder          Card holders name
    * @param cardNumber      Long card number
    * @param cv              CV verification code
    * @param expirationMonth Card expiration month
    * @param expirationYear  Card expiration month
    */
  def addCard(accountID: String, holder: String, cardNumber: String, cv: String, expirationMonth: String, expirationYear: String): Unit = {
    val tmp = randomID
    cardList += CardDetails(tmp, holder, cardNumber, cv, expirationMonth, expirationYear)
    cardMap += CardMap(accountID, tmp)
  }

  /**
    * this function will get card details using User ID
    *
    * @param cardID User ID
    * @return CardDetails object
    */
  def getCards(cardID: String): Array[CardDetails] = {
    val cardIDs = cardMap.filter(_.accountID == cardID)
    var toReturn = ArrayBuffer[CardDetails]()
    for (card <- cardIDs) {
      toReturn += cardList.find(_.cardID == card.cardID).get
    }

    toReturn.toArray
  }

  /**
    * this function will remove customers card
    *
    * @param cardNumber Long card number
    */
  def removeCard(cardNumber: String): Unit = {
    val card = cardList.find(_.cardNumber == cardNumber).get
    cardList.remove(cardList.indexOf(card))
    cardMap.remove(cardMap.indexOf(cardMap.find(_.cardID == card.cardID).get))

  }
}
