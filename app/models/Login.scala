package models

import java.lang.ProcessBuilder.Redirect

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

case class Login(lid: String, var email: String, var pass: String) {}

object Login {

  // dummy data
  private var list = ArrayBuffer[Login](
    Login("l0", "a", "a")
  )

  def createUser(name: String, password: String, email: String): Unit = {

    val randomUID = java.util.UUID.randomUUID.toString
    list += Login(randomUID, email, password)
  }

  def findLogin(email: String) = list.find(_.email == email.toLowerCase)

  def findLoginByID(lid: String) = list.find(_.lid == lid)

}
