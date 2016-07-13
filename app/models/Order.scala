package models

import java.util.{Calendar, Date}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Paul on 13/07/2016.
  */
case class Order (id: Int, items:ArrayBuffer[OrderLine], time:Date){

}

object Order{
  val orderList = ArrayBuffer[Order](
    Order(1, OrderLine.basket, today),
    Order(2, OrderLine.basket,today)
  )

  val today = Calendar.getInstance.getTime

}