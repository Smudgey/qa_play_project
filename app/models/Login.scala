package models

import scala.collection.mutable.ArrayBuffer


/*
  * Create By rytis
  *
  * Last worked on by Rytis on 26/07/2916
  */

/**
  * this case class will represent login details for the user
  * @param lid Login ID
  * @param email Username
  * @param pass Password
  */
case class Login(lid: String, var email: String, var pass: String) {}

object Login extends Formatter {

  // dummy data
  private var list = ArrayBuffer[Login](
    Login("l0", "a", "a")
  )

  /**
    * this function will create user logij
    * @param lid LoginID
    * @param email Username
    * @param password Password
    */
  def createUser(lid: String, email: String, password: String): Unit = {
    list += Login(lid, email, password)
  }

  /**
    * this function will get login object by email address
    * @param email Email address
    * @return
    */
  def findLogin(email: String) = list.find(_.email == email.toLowerCase)

  /**
    * this function will find login object by ID
    * @param lid Login ID
    * @return
    */
  def findLoginByID(lid: String) = list.find(_.lid == lid)

}
