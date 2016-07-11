package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 06/07/2016.
  */
case class Product(pid: Int, name: String, description: String, stock: Int, pwareStock: Int, price: Double) {}

object Product {

  var list = new ArrayBuffer[Product]

  def generate(): Unit = {
    list.clear()
    this.add(Product(701, "Justin Bieber Gnome", "This is a small gnome with bad hair.", 6, 0, 99.99))
    this.add(Product(702,"Sexy Gnome", "This is a large sexy gnome dressed in a bikini", 87, 58, 11.99))
    this.add(Product(703,"Red Gnome", "A small red gnome", 14, 2, 6.99))
    this.add(Product(704,"Rytis Gnome", "A funny looking gnome, angry face.", 2, 1, 5.98))
    this.add(Product(705,"Harrison Gnome", "Does it even lift though?", 23, 4, 3.58))
    this.add(Product(706,"Arnie Gnome", "GET TO DEE CHOPPAAA", 23, 4, 9.99))
    this.add(Product(707,"Yang Gnome", "Jar Jar fanatic gnome with a bowl of oats", 11, 11, 6.66))
    this.add(Product(708,"Paul Gnome","Will send you back to Camden", 12, 1, 5.99))
    this.add(Product(709,"Leprechaun Gnome","A gnome dressed in green with a pot of gold", 5,0,10.05))
    this.add(Product(710,"Plant Pot","A small decorated plant pot suitable for potting plants", 25, 0,10.99))
    this.add(Product(711,"Novelty Water Fountain","Lorem ipsum dictatotoprjsdfjahlsdjgfas", 12, 0, 58.99))
    this.add(Product(712,"Rake","Because Yang said it", 5, 0, 8.99))
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

  /*  This function returns an Option{Product}.  To access elements within the object call;
          <your Product List>.findProduct( pid:Int ).get.<YOUR VARIABLE>
   */
  def findProduct(pid:Int) = list.find(_.pid == pid)
}
