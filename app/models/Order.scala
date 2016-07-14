package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Marko on 14/07/2016.
  */
class Order(oli: OrderLine, totalPrice: Double, status: OrderStatus.Value, paymentMethod: PaymentMethod.Value, account: Int) {

  val orderID = Order.nextID


}

object Order{

  var orderList = getOrders

  private def getOrders: ArrayBuffer[Order] = {
    ArrayBuffer[Order](
      new Order(OrderLine.basket(0), 1.1, OrderStatus.Ordered, PaymentMethod.PayNow, 3),
      new Order(OrderLine.basket(1), 1.1, OrderStatus.Ordered, PaymentMethod.PayNow, 4),
      new Order(OrderLine.basket(2), 1.1, OrderStatus.Ordered, PaymentMethod.PayNow, 5),
      new Order(OrderLine.basket(3), 1.1, OrderStatus.Ordered, PaymentMethod.PayNow, 6)
    )
  }

  implicit def nextID: Int = {
    if(orderList.isEmpty) {
      println(0)
      0
    } else {
      val ord = orderList.groupBy(_.orderID)
      println(ord)
      3
    }
  }

  def main(args: Array[String]) {
    nextID
  }
}

