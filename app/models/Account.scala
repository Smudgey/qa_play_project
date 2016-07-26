package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by rytis on 25/07/16.
  */
case class Account(accountID: String, lid: String, details: String, addresses: ArrayBuffer[String], cards: ArrayBuffer[String]) {}

object Account {
  private val accountList = ArrayBuffer[Account](
    Account("0", "l0", "d0", ArrayBuffer[String]("a0", "a1"), ArrayBuffer[String]("c0", "c1"))
  )

  def getAccountViaEmail(lid: String) = accountList.find(_.lid == lid)
}