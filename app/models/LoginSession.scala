package models

object LoginSession {
  private var currentUser = ""

  def setUser(uid: String): Unit = currentUser = uid

  def getCurrentUser = currentUser
}
