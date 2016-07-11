package models

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

case class Login(lid: String, name: String, pass: String, email: String) {}

object Login {

  // dummy data
  private var list = ArrayBuffer[Login](
    Login("101", "dave", "1234", ""),
    Login("102", "betty", "1235", ""),
    Login("103", "bob", "pass", "bob@bob.com")
  )

  def createUser(name: String, password: String, email: String): Boolean = {
    if (findLogin(email).isEmpty) {
      list += Login(Random.nextString(10), name, password, email)
      true
    } else {
      false
    }
  }

  def findLogin(email: String) = list.find(_.email == email.toLowerCase)
}
