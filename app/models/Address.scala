package models

import scala.collection.mutable.ArrayBuffer

/*
  * Create By rytis
  *
  * Last worked on by Rytis on 26/07/2916
  */

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

case class AddressMap(accountID: String, addressID: String) {}

object Address extends Formatter{
  private var addressList = ArrayBuffer[Address](
    Address("0", "a", "a", "a", "a", "a", "a"),
    Address("1", "b", "b", "b", "b", "b", "b")
  )

  private var addressMap = ArrayBuffer[AddressMap](
    AddressMap("a0", "0"),
    AddressMap("a0", "1")
  )


  /**
    * this function will add customer address to storage
    *
    * @param accountID   AccountID
    * @param houseNumber House number
    * @param streetName  Street name
    * @param town        town
    * @param city        City
    * @param county      County
    * @param postcode    Postcode
    */
  def addAddress(accountID: String, houseNumber: String, streetName: String, town: String, city: String, county: String, postcode: String): Unit = {
    val tmp = randomID
    addressList += Address(tmp, houseNumber, streetName, town, city, county, postcode)
    addressMap += AddressMap(accountID, tmp)
  }

  /**
    * this function will get all of the customer address via AddressID
    * @param addressID AddressID
    * @return Array of Address objects
    */
  def getAddress(addressID: String): Array[Address] = {
    val addressIDs = addressMap.filter(_.accountID == addressID)
    var toReturn = ArrayBuffer[Address]()
    for (address <- addressIDs) {
      toReturn += addressList.find(_.addressID == address.addressID).get
    }
    toReturn.toArray
  }

  /**
    * this function will remove customer address by address ID
    * @param addressID AddressID
    */
  def removeAddress(addressID: String): Unit = {
    val card = addressList.find(_.addressID == addressID).get
    addressList.remove(addressList.indexOf(card))
    addressMap.remove(addressMap.indexOf(addressMap.find(_.addressID == card.addressID).get))

  }

}

