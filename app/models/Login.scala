package models

import java.lang.ProcessBuilder.Redirect

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

case class Login(lid: String, name: String, pass: String, email: String) {}

object Login {

  private var loggedIn = false

  // dummy data
  private var list = ArrayBuffer[Login](
    Login("101", "dave", "1234", ""),
    Login("102", "betty", "1235", ""),
    Login("103", "bob", "pass", "bob@bob.com"),
    Login("000", "a", "a", "a")
  )

  def createUser(name: String, password: String, email: String): Boolean = {
    if (findLogin(email).isEmpty) {
      val randomUID = Random.nextString(10)
      LoginSession.setUser(randomUID)
      list += Login(randomUID, name, password, email)
      true
    } else {
      false
    }

  }

  def findLogin(email: String) = list.find(_.email == email.toLowerCase)

  def toggleLogin(): Unit = {
    if (loggedIn) {
      loggedIn = false
      // TODO logout message
    }  else {
      loggedIn = true
    }
  }

  def getStatus() = {
    loggedIn
  }
}
