package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 06/07/2016.
  */
class ProductList {

  var list = new ArrayBuffer[Product]

  def generate(): Unit = {
    this.add(Product(701, "Justin Bieber Gnome", "This is a small gnome with bad hair.", 6, 0, 99.99))
    this.add(Product(702,"Sexy Gnome", "This is a large sexy gnome dressed in a bikini", 87, 58, 11.99))
    this.add(Product(703,"Red Gnome", "A small red gnome", 14, 2, 6.99))
    this.add(Product(704,"Rytis Gnome", "A funny looking gnome, angry face.", 2, 1, 5.98))
    this.add(Product(705,"Harrison Gnome", "Does it even lift though?", 23, 4, 3.50))
    this.add(Product(706,"Arnie Gnome", "GET TO DEE CHOPPAAA", 23, 4, 9.99))
    this.add(Product(707,"Yang Gnome", "Jar Jar fanatic gnome with a bowl of oats", 11, 11, 6.66))
  }

  def add(prod: Product): Unit = {
    list += prod
  }

  def print(): String = {

    val str = "The big red dog sucks on bones"
//    var str = ""
//    str += "Products\n"
//    for (product <- this) {
//      str += "- " + product.name
//    }
    str
  }


}
