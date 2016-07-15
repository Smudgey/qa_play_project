package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Marko on 14/07/2016.
  */
class Order(oli: ArrayBuffer[OrderLine], totalPrice: Double, status: OrderStatus.Value, paymentMethod: PaymentMethod.Value, account: Int) {

  val orderID = java.util.UUID.randomUUID
}

object Order{



}

