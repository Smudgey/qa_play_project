package models

import reactivemongo.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter}

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
    def write(account_New: Address_New): BSONDocument =
      BSONDocument(
        "AddressLine1" -> account_New.addressLineOne,
        "AddressLine2" -> account_New.addressLineTwo,
        "AddressCity" -> account_New.city,
        "AddressCounty" -> account_New.county,
        "AddressPostcode" -> account_New.postcode

      )
  }

}



