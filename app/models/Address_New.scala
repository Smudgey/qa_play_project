package models

import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by rytis on 28/07/16.
  */
case class Address_New(addressLineOne: String, addressLineTwo: String, city: String, county: String, postcode: String) {}

object Address_New {

  implicit object AddressReader extends BSONDocumentReader[Address_New] {
    def read(doc: BSONDocument): Address_New =
      Address_New(
        doc.getAs[String]("AddressLine1").get,
        doc.getAs[String]("AddressLine2").get,
        doc.getAs[String]("AddressCity").get,
        doc.getAs[String]("AddressCounty").get,
        doc.getAs[String]("AddressPostcode").get
      )
  }

  implicit object AddressWriter extends BSONDocumentWriter[Address_New] {
    def write(address: Address_New): BSONDocument =
      BSONDocument(
        "AddressLine1" -> address.addressLineOne,
        "AddressLine2" -> address.addressLineTwo,
        "AddressCity" -> address.city,
        "AddressCounty" -> address.county,
        "AddressPostcode" -> address.postcode

      )
  }

  def create(addressCollection: BSONCollection, address: Address_New)(implicit ec: ExecutionContext, writer: BSONDocumentWriter[Address_New]): Future[Unit] = {
    val writeResult = addressCollection.insert(address)
    writeResult.map(_ => {
      /*presumably this is just using the writer implicitly to know how to output every key we loop through
      * as a valid entry in a BSONDocument as an Account_New object.... I'll try to improve this description later*/
    })
  }

}



