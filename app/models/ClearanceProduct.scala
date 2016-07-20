package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Marko on 20/07/2016.
  */
class ClearanceProduct(product: Product, clearancePrice: Double, quant: Int) {

  {
    ClearanceProduct.clearanceList += product
  }

}

object ClearanceProduct {

  var clearanceList = new ArrayBuffer[Product]()

  def markProductAsClearance(prod: Product): Unit = {
    println()
  }


}
