package models

import java.text.SimpleDateFormat
import java.util.Calendar

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Paul on 13/07/2016.
  */

case class Order(customerID: String, orderID: Int, orderLines: ArrayBuffer[OrderLine], var totalPrice: Double, status: OrderStatus.Value, paymentMethod: PaymentMethod.Value, time: String, account: Int) {}

object Order {
  private val today = new SimpleDateFormat("hh:mm aa d-M-y").format(Calendar.getInstance().getTime)

  private val orderList = ArrayBuffer[Order](
    Order("000", 1,
      ArrayBuffer[OrderLine](
        OrderLine(Product.findProduct(701).get, 3, 1),
        OrderLine(Product.findProduct(702).get, 2, 0)
      ),
      0,
      OrderStatus.Dispatched,
      PaymentMethod.PayLater,
      today, 4),
    Order("000", 2,
      ArrayBuffer[OrderLine](
        OrderLine(Product.findProduct(703).get, 2, 1)
      ),
      0,
      OrderStatus.Dispatched,
      PaymentMethod.PayLater,
      today, 4)
  )

  getTotalPrice

  def getTotalPrice: Unit = {
    for (order <- orderList) {
      order.totalPrice = OrderLine.totalPrice(order.orderLines)
    }
  }

  def getOrderByID(orderID: Int) = orderList.find(_.orderID == orderID)

  def getOrderHistory(customerID: String) = orderList.filter(_.customerID == customerID)

}
