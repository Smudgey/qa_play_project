package models

import scala.collection.mutable.ArrayBuffer


case class CustomerDetails(cid: String, name: String, addressLine: String, city: String, county: String, postCode: String) {}

object CustomerDetails {
  private val detailsList = ArrayBuffer[CustomerDetails](
    CustomerDetails("d0", "a", "a", "a", "a", "a")
  )

  def getDetails(cid: String) = detailsList.find(_.cid == cid)
}

