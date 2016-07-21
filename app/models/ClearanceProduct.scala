package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Marko on 20/07/2016.
  */
case class ClearanceProduct(pid: Int, clearancePrice: Double, quantity: Int) {

  {
    ClearanceProduct.clearanceList += this
  }

}

object ClearanceProduct {

  var clearanceList = new ArrayBuffer[ClearanceProduct]()

  def markExistingProductAsClearance(pid: Int, newPrice: Double, quantity: Int): Unit = {

    ClearanceProduct(pid, newPrice, quantity)
    Product.findProduct(pid).get.decrementStock(quantity, 0)
  }


}
