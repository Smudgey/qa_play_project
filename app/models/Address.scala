package models

import scala.collection.mutable.ArrayBuffer

/**
  * this class will represent user address
  *
  * @param addressID   Address ID
  * @param houseNumber House number
  * @param streetName  Street name
  * @param town        town
  * @param city        City
  * @param county      County
  * @param postcode    Postcode
  */
case class Address(addressID: String, houseNumber: String, streetName: String, town: String, city: String, county: String, postcode: String) {}

object Address {
  private var addressList = ArrayBuffer[Address](
    Address("a0", "a", "a", "a", "a", "a", "a"),
    Address("a1", "b", "b", "b", "b", "b", "b")
  )


  /**
    * this function will add customer address to storage
    *
    * @param uid         User ID
    * @param houseNumber House number
    * @param streetName  Street name
    * @param town        town
    * @param city        City
    * @param county      County
    * @param postcode    Postcode
    */
  def addAddress(uid: String, houseNumber: String, streetName: String, town: String, city: String, county: String, postcode: String): Unit = {

    addressList += Address(uid, houseNumber, streetName, town, city, county, postcode)
  }

  /**
    * this function will return customer address
    *
    * @param addressID AddressID
    * @return Address object
    */
  def getAddress(addressID: String) = addressList.find(_.addressID == addressID)
}