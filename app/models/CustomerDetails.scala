package models

import scala.collection.mutable.ArrayBuffer


case class CustomerDetails(eMail: String, phoneNumber: String, fName: String, lName: String, addressLine1: String, addressLine2: String, city: String, county: String, postCode: String) {}

object CustomerDetails {
  var detailsList:ArrayBuffer[CustomerDetails] = ArrayBuffer.empty
  detailsList += CustomerDetails("yahoo", "1215416", "Harrison", "Baxter", "Some address", "some address", "London", "London", "some post code")
  detailsList += CustomerDetails("gmail", " ", " ", " ", " ", " ", " ", " ", " ")
  detailsList += CustomerDetails("btInternet", " ", " ", " ", " ", " ", " ", " ", " ")
  detailsList += CustomerDetails("Live", " ", " ", " ", " ", " ", " ", " ", " ")
  detailsList += CustomerDetails("AOL", " ", " ", " ", " ", " ", " ", " ", " ")
  detailsList += CustomerDetails("Verizon", " ", " ", " ", " ", " ", " ", " ", " ")
  detailsList += CustomerDetails("outlook", " ", " ", " ", " ", " ", " ", " ", " ")
  detailsList += CustomerDetails("mail", " ", " ", " ", " ", " ", " ", " ", " ")
  detailsList += CustomerDetails("pgp", " ", " ", " ", " ", " ", " ", " ", " ")
  detailsList += CustomerDetails("pop", " ", " ", " ", " ", " ", " ", " ", " ")
  detailsList += CustomerDetails("a", "07981918209", "Yang", "Wong", "1 road", "2 place", "Gungan city", "Middle-earth", "f6 911")


  def updateAccount(details: CustomerDetails): Unit = {
    var returnList: ArrayBuffer[CustomerDetails] = ArrayBuffer.empty
    def looklist(list: ArrayBuffer[CustomerDetails]): ArrayBuffer[CustomerDetails] = {
      if (list.isEmpty) {
        returnList
      }
      else if (list.head.eMail == details.eMail) {
        returnList += details
      }
      else {
        returnList += list.head
        looklist(list.tail)
      }
    }
    detailsList = looklist(detailsList)
  }

  def findCustomerEmail(eMail: String): ArrayBuffer[CustomerDetails] = {
    var returnList: ArrayBuffer[CustomerDetails] = ArrayBuffer.empty
    def looklist(list: ArrayBuffer[CustomerDetails]): ArrayBuffer[CustomerDetails] = {
      if (list.isEmpty) {
        returnList
      }
      else if (list.head.eMail == eMail) {
        returnList += list.head
      }
      else {
        looklist(list.tail)
      }
    }
    looklist(detailsList)
  }

}

