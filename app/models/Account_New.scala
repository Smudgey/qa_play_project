package models

import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONObjectID}

import scala.concurrent.{ExecutionContext, Future}

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

  implicit object AccountWriter extends BSONDocumentWriter[Account_New] {


    def write(account_New: Account_New): BSONDocument = {


      BSONDocument(
        "ID" -> account_New.accountID,
        "Username" -> account_New.username,
        "Password" -> account_New.accountID,
        "Name" -> account_New.accountID,
        "Phone" -> account_New.accountID,
        "Address" -> account_New.address,
        "PaymentCards" -> account_New.paymentCards

      )
    }

  }

  def create(personCollection: BSONCollection, account: Account)(implicit ec: ExecutionContext): Future[Unit] = {
    val writeResult = personCollection.insert(account)
    writeResult.map(_ => {
      /*once this is successful, just return successfully*/
    })
  }

}
