package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Marko on 20/07/2016.
  */
case class ClearanceProduct(pid: Int, clearancePrice: Double, quantity: Int) {

  {
    ClearanceProduct.clearanceList += Product.findProduct(pid).get
  }

}

object ClearanceProduct {

  var clearanceList = new ArrayBuffer[Product]()

  def markProductAsClearance(pid: Int, newPrice: Double, quantity: Int): Unit = {
    Product.findProduct(pid).get.decrementStock(quantity, 0)
  }

}
