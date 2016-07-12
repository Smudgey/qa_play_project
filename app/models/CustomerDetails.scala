package models

import scala.collection.mutable.ArrayBuffer


case class CustomerDetails (eMail: String, phoneNumber: String, fName: String, lName: String, addressLine1: String, addressLine2: String, city: String, county: String, postCode: String ){}

object CustomerDetails {
  var detailsList = new ArrayBuffer[CustomerDetails]
  (CustomerDetails("yahoo", "1215416", "Harrison", "Baxter", "Some address", "some address", "London", "London", "some post code"),
    ("gmail", " ", " ", " ", " ", " ", " ", " ", " "),
    ("btInternet", " ", " ", " ", " ", " ", " ", " ", " "),
    ("Live", " ", " ", " ", " ", " ", " ", " ", " "),
    ("AOL", " ", " ", " ", " ", " ", " ", " ", " "),
    ("Verizon", " ", " ", " ", " ", " ", " ", " ", " "),
    ("outlook", " ", " ", " ", " ", " ", " ", " ", " "),
    ("mail", " ", " ", " ", " ", " ", " ", " ", " "),
    ("pgp", " ", " ", " ", " ", " ", " ", " ", " "),
    ("pop", " ", " ", " ", " ", " ", " ", " ", " "))


  def updateAccount(detail: CustomerDetails): Unit = {

  }

  def findCustomerEmail(eMail: String): ArrayBuffer[CustomerDetails] ={
    var returnList = new ArrayBuffer[CustomerDetails] = ArrayBuffer.empty
    def looklist (list: ArrayBuffer[CustomerDetails]): ArrayBuffer[CustomerDetails] ={
      if (list.isEmpty){
        returnList
      }
      else if (list.head.eMail == eMail){
        returnList :+ list.head
      }
      else{
        looklist(list.tail)
      }
    }
    looklist(detailsList)
  }
}

