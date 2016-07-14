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
  var size = getSize

  def totalPrice(bsk: ArrayBuffer[OrderLine]): Double = {
    def addToTot(bsk: ArrayBuffer[OrderLine], total: Double): Double = {
      if (bsk.isEmpty)
        BigDecimal(total).setScale(2, BigDecimal.RoundingMode.UP).toDouble
      else
        addToTot(bsk.tail, total + bsk.head.prod.price * bsk.head.quantity)
    }
    addToTot(bsk, 0)
  }

  def clear(): Unit = {
    for(ol <- basket) {
      val product = Product.findProduct(ol.prod.pid).get
      product.stock       += ol.quantity
      product.pwareStock  += ol.pwareQuantity
    }
    basket.clear()

  }

  private def getSize: Int = {
    def accumulate(bsk: ArrayBuffer[OrderLine], total: Int): Int = {
      if(bsk.isEmpty)
        total
      else
        accumulate(bsk.tail, total + bsk.head.quantity)
    }
    accumulate(basket, 0)
  }

  def removeItem(pid: Int): Unit = {

    size -= findOrderLine(pid).get.quantity
    basket.remove(basket.indexOf(findOrderLine(pid).get))
  }

  def addToBasket(oli: OrderLine) {
    //Do product stock validation here
    oli.prod.decrementStock(oli.quantity, oli.pwareQuantity)
    size += oli.quantity + oli.pwareQuantity

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


