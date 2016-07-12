package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Marko on 11/07/2016.
  *
  * The orderline class takes in a product, an order quantitry and a porousware quantity.  A product is passed rather than a product ID so that the objects variables can be called
  * within this class, rather than
  */
case class OrderLine(prod: Product, var quantity: Int = 1, var pwareQuantity: Int = 0) {}

object OrderLine {

  var basket = new ArrayBuffer[OrderLine]

  def totalPrice(bsk: ArrayBuffer[OrderLine], total: Double = 0): Double = {

    var newTotal = total
    if(bsk.isEmpty){newTotal}
    else {
      newTotal += bsk.head.prod.price * bsk.head.quantity
      totalPrice(bsk.tail, newTotal)
    }
    BigDecimal(newTotal).setScale(2, BigDecimal.RoundingMode.UP).toDouble
  }

  def clear(): Unit = {
    for(ol <- basket) {
      val product = Product.findProduct(ol.prod.pid).get
      product.stock       += ol.quantity
      product.pwareStock  += ol.pwareQuantity
    }
    basket.clear()
  }

  def addToBasket(oli: OrderLine) {
    //Do product stock validation here
    oli.prod.decrementStock(oli.quantity, oli.pwareQuantity)

      def addOrIncrease(bsk: ArrayBuffer[OrderLine], oli2: OrderLine): Unit ={
        if( bsk.isEmpty ){
          basket += oli2
        } else if(bsk.head.prod.pid == oli2.prod.pid) {
          bsk.head.quantity       += oli2.quantity
          bsk.head.pwareQuantity  += oli2.pwareQuantity
      } else {
          addOrIncrease(bsk.tail, oli2)
        }
    }
    addOrIncrease(basket, oli)
  }

  def findOrderLine(pid:Int) = basket.find(_.prod.pid == pid)
}

