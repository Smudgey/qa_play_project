package models

import reactivemongo.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter}

/**
  * Created by rytis on 28/07/16.
  */
case class PaymentCards(name:String, cardNumber:String, cardExpiry:String){}

object PaymentCards {
  implicit object PaymentCardsReader extends BSONDocumentReader[PaymentCards] {
    def read(doc: BSONDocument): PaymentCards =
      PaymentCards(
        doc.getAs[String]("CardName").get,
        doc.getAs[String]("CardNumber").get,
        doc.getAs[String]("CardExpiry").get
      )
  }

  implicit object PaymentCardsWriter extends BSONDocumentWriter[PaymentCards] {
    def write(paymentCards: PaymentCards): BSONDocument =
      BSONDocument(
        "CardName" -> paymentCards.name,
        "CardNumber" -> paymentCards.cardNumber,
        "CardExpiry" -> paymentCards.cardExpiry
      )
  }
}