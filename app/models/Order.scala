package models

import java.text.SimpleDateFormat
import java.util.Calendar

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Paul on 13/07/2016.
  *
  * Last worked on by Rytis on 25/07/2016
  *
  */

case class Order(customerID: String, orderID: String, orderLines: ArrayBuffer[OrderLine], var totalPrice: Double, status: OrderStatus.Value, paymentMethod: PaymentMethod.Value, time: String, var rating: Int) {}

object Order {

  // this will give today's timestamp
  val today = new SimpleDateFormat("hh:mm aa d-M-y").format(Calendar.getInstance().getTime)

// dummy data
  private val orderList = ArrayBuffer[Order](
    Order("000", randomOID,
      ArrayBuffer[OrderLine](
        OrderLine(Product.findProduct(701).get, 3, 1),
        OrderLine(Product.findProduct(702).get, 2, 0)
      ),
      0,
      OrderStatus.Dispatched,
      PaymentMethod.PayLater,
      today, 3),
    Order("000", randomOID,
      ArrayBuffer[OrderLine](
        OrderLine(Product.findProduct(703).get, 2, 1)
      ),
      0,
      OrderStatus.Dispatched,
      PaymentMethod.PayLater,
      today, 5)
  )

  // calculates dummy orders prices
  totalPrice()

  /**
    * this function will loop through orders and total up order items
    */
  def totalPrice(): Unit = {
    for (order <- orderList) {
      order.totalPrice = OrderLine.totalPrice(order.orderLines)
    }
  }

  /**
    * this function will get order object by ID
    * @param orderID Order ID
    * @return
    */
  def getOrderByID(orderID: String) = orderList.find(_.orderID == orderID)

  /**
    * this function will find customers purchase history via Customer ID
    * @param customerID Customer ID
    * @return
    */
  def getOrderHistory(customerID: String) = orderList.filter(_.customerID == customerID)


  /**
    * this function will set star rating for purchase order
    * @param orderID Order ID
    * @param rating User input star rating
    */
  def setStarRating(orderID: String, rating: Int): Unit = {
    getOrderByID(orderID).get.rating = rating
  }

  /**
    * this function will generated random order id string
    * @return Random orderID
    */
  def randomOID: String = {
    java.util.UUID.randomUUID.toString
  }
}
