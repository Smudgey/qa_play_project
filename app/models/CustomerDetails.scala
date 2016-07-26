package models

import scala.collection.mutable.ArrayBuffer


case class CustomerDetails(cid: String, var name: String, var telephoneNumber: String) {}

object CustomerDetails {
  private val detailsList = ArrayBuffer[CustomerDetails](
    CustomerDetails("d0", "a", "000")
  )

  def getDetails(cid: String) = detailsList.find(_.cid == cid)

  def updateDetails(cid: String, name: String, telephoneNumber: String, email: String): Unit = {
    val customer = detailsList.find(_.cid == cid).get
    customer.telephoneNumber = telephoneNumber
    customer.name = name
    val account = Account.getAccountViaDetailsID(cid).get
    Login.findLoginByID(account.lid).get.email = email
  }
}

