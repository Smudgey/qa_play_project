package models

import reactivemongo.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONObjectID}

/**
  * Created by rytis on 28/07/16.
  */

case class Account_New(accountID: String, username: String, password: String, name: String, phone: String, address: Array[Address_New], paymentCards: Array[PaymentCards]) {}

object Account_New {

  implicit object AccountReader extends BSONDocumentReader[Account_New] {
    def read(doc: BSONDocument): Account_New =
      Account_New(
        doc.getAs[String]("ID").get,
        doc.getAs[String]("Username").get,
        doc.getAs[String]("Password").get,
        doc.getAs[String]("Name").get,
        doc.getAs[String]("Phone").get,
        doc.getAs[Array[Address_New]]("Address").get,
        doc.getAs[Array[PaymentCards]]("PaymentCards").get)


  }

  /* implicit object AccountWritter extends BSONDocumentWriter[Account_New] {
     def write(Account_New: Account_New): BSONDocument =
       BSONDocument("name" -> person.name)
   }
 */
}
