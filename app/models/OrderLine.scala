package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Marko on 11/07/2016.
  *
  * The orderline class takes in a product, an order quantitry and a porousware quantity.  A product is passed rather than a product ID so that the objects variables can be called
  * within this class, rather than
  */
case class OrderLine(prod: Product, quantity: Int = 1, pwareQuantity: Int = 0) {}

  object OrderLine {

    var oline = new ArrayBuffer[OrderLine]

    def addToBasket(oli: OrderLine){
      //Do product stock validation here
      oli.prod.decrementStock(oli.quantity, oli.pwareQuantity)
      oline += oli
    }
  }


