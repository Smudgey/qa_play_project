package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 07/07/2016.
  */
class LoginList {

  var list = new ArrayBuffer[Login]

  def generate(): Unit = {
    this.add(Login(101,"Dave","1234"))
    this.add(Login(102,"Betty","1235"))
    this.add(Login(103,"Bob","1236"))
  }

  def add(login: Login): Unit = {
    list += login
  }

  def print(): String = {
    ""
  }

}
