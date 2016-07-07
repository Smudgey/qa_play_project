package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 07/07/2016.
  */
case class Login(lid: Int, name: String, pass: String) {}

object Login {

  var list = new ArrayBuffer[Login]

  def generate(): Unit = {
    this.add(Login(101, "Dave", "1234"))
    this.add(Login(102, "Betty", "1235"))
    this.add(Login(103, "Bob", "1236"))
  }

  def add(login: Login): Unit = {
    list += login
  }

  /*  This function returns an Option{Login}.  To access elements within the object call;
          <your Login List>.findLogin( name:String ).get.<YOUR VARIABLE>
   */
  def findLogin(name: String) = list.find(_.name == name)

  def printLogin(login: Login): Unit = {

  }

}
