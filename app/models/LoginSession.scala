package models

object LoginSession {
  private var currentUser = ""
  private var searchQuery = ""

  def setUser(uid: String): Unit = currentUser = uid

  def getCurrentUser = currentUser
}
