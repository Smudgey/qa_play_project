package models

import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by rytis on 28/07/16.
  */
case class Address(addressLineOne: String, addressLineTwo: String, city: String, county: String, postcode: String) {}

object Address {

  implicit object AddressReader extends BSONDocumentReader[Address] {
    def read(doc: BSONDocument): Address =
      Address(
        doc.getAs[String]("AddressLine1").get,
        doc.getAs[String]("AddressLine2").get,
        doc.getAs[String]("AddressCity").get,
        doc.getAs[String]("AddressCounty").get,
        doc.getAs[String]("AddressPostcode").get
      )
  }

  implicit object AddressWriter extends BSONDocumentWriter[Address] {
    def write(address: Address): BSONDocument =
      BSONDocument(
        "AddressLine1" -> address.addressLineOne,
        "AddressLine2" -> address.addressLineTwo,
        "AddressCity" -> address.city,
        "AddressCounty" -> address.county,
        "AddressPostcode" -> address.postcode

      )
  }

  def create(addressCollection: BSONCollection, address: Address)(implicit ec: ExecutionContext, writer: BSONDocumentWriter[Address]): Future[Unit] = {
    val writeResult = addressCollection.insert(address)
    writeResult.map(_ => {
      /*presumably this is just using the writer implicitly to know how to output every key we loop through
      * as a valid entry in a BSONDocument as an Account object.... I'll try to improve this description later*/
    })
  }

}



