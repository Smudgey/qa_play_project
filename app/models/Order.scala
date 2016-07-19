package models

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Paul on 13/07/2016.
  */
case class Order(id: Int, orderLines:ArrayBuffer[OrderLine], totalPrice:Double, status:OrderStatus.Value, paymentMethod: PaymentMethod.Value, time:String = Order.today, account:Int){

  val orderID = java.util.UUID.randomUUID
  val payment
}

object Order{

  val orderList = new ArrayBuffer[Order]
  val today = format.format(Calendar.getInstance().getTime)
  val format = new SimpleDateFormat("hh:mm aa d-M-y")

  def generate: Unit = {

    val ol1 = ArrayBuffer(new OrderLine(Product.findProduct(701).get, 3, 1), new OrderLine(Product.findProduct(702).get, 2, 0) )
    val ol2 = ArrayBuffer(new OrderLine(Product.findProduct(703).get, 2, 1))

    this.add(new Order(1,
      ol1,
      OrderLine.totalPrice(ol1), OrderStatus.Dispatched, PaymentMethod.PayLater, Order.today, 4))
    this.add( new Order(2,
      ol2,
      OrderLine.totalPrice(ol2), OrderStatus.Dispatched, PaymentMethod.PayLater, Order.today, 4))
  }
  def add(ord: Order): Unit = {
    orderList += ord
  }

  def main(args:Array[String]): Unit ={
    println(orderList)
  }




}
