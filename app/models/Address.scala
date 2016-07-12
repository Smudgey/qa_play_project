package models

import scala.collection.mutable.ArrayBuffer

/**
  * this class will represent user address
  *
  * @param uid         User ID
  * @param houseNumber House number
  * @param streetName  Street name
  * @param town        town
  * @param city        City
  * @param county      County
  * @param postcode    Postcode
  */
case class Address(uid: String, houseNumber: String, streetName: String, town: String, city: String, county: String, postcode: String) {}

object Address {
  private var addressList = ArrayBuffer[Address]()


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
    * @param uid User ID
    * @return Address object
    */
  def getAddress(uid: String) = addressList.find(_.uid == uid)
}