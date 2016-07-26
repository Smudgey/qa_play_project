package models

import scala.collection.mutable.ArrayBuffer

/*
  * Create By rytis
  *
  * Last worked on by Rytis on 26/07/2916
  */

/**
  * this case class will represent customer account
  *
  * @param accountID Unique AccountID
  * @param loginID   Unique LoginID
  * @param detailsID Unique DetailsID
  * @param addressID Unique AddressID
  * @param cardID    Unique CardID
  */
case class Account(accountID: String, var loginID: String, var detailsID: String, var addressID: String, var cardID: String) {}

object Account extends Formatter {
  //dummy data
  private val accountList = ArrayBuffer[Account](
    Account("0", "l0", "d0", "a0", "c0")
  )

  /**
    * this function will get account object by loginID
    *
    * @param lid Login ID
    * @return
    */
  def getAccountViaEmail(lid: String) = accountList.find(_.loginID == lid)

  /**
    * this function will get account details by cusomter information details
    *
    * @param detailsID Customer details ID
    * @return
    */
  def getAccountViaDetailsID(detailsID: String) = accountList.find(_.detailsID == detailsID)

  /**
    * this function will create new account
    *
    * @param accountID Random AccountID
    * @param loginID   Random LoginID
    * @param detailsID Random DetailsID
    */
  def createAccount(accountID: String, loginID: String, detailsID: String): Unit = {
    accountList += Account(accountID, loginID, detailsID, "", "")

  }
}