package models

import scala.collection.mutable.ArrayBuffer

/*
  * Create By rytis
  *
  * Last worked on by Mark on 09/08/2016
  */

/**
  * this case class will represent customer details
  * @param cid Customer ID
  * @param fname Customer first name
  * @param lname Customer last nale
  * @param telephoneNumber customer telephone number
  */
case class CustomerDetails(cid: String, var fname: String, var lname: String, var telephoneNumber: String) {}

//object CustomerDetails {
//  //dummy data
//  private val detailsList = ArrayBuffer[CustomerDetails](
//    CustomerDetails("d0", "a", "000")
//  )
//
//  /**
//    * this function will return customer details object
//    * @param cid CustomerID
//    * @return
//    */
//  def getDetails(cid: String) = detailsList.find(_.cid == cid)
//
//  /**
//    * this function will update customer details
//    * @param cid customerID
//    * @param name Customer Name
//    * @param telephoneNumber Customer name
//    * @param email Customer Name
//    */
////  def updateDetails(cid: String, name: String, telephoneNumber: String, email: String): Unit = {
////    val customer = detailsList.find(_.cid == cid).get
////    customer.telephoneNumber = telephoneNumber
////    customer.name = name
////
////    val account = Account.getAccountViaDetailsID(cid).get
////    Login.findLoginByID(account.loginID).get.email = email
////  }
//
//  def addDetails(cid: String, name: String, telephoneNumber: String): Unit = {
//    detailsList += CustomerDetails(cid, name, telephoneNumber)
//  }
//}

