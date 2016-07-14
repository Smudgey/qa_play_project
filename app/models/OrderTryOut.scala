package models

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Paul on 13/07/2016.
  */
case class OrderTryOut(id: Int, items:ArrayBuffer[OrderLine], status:String, time:String){

}

object OrderTryOut{

  val format = new SimpleDateFormat("hh:mm aa d-M-y")

//  val itemList1 = ArrayBuffer[Product]{
//    Product()
//  }

//  val today = format.format(Calendar.getInstance().getTime())

  val today = format.format(Calendar.getInstance().getTime())

  val orderList = ArrayBuffer[OrderTryOut](
    OrderTryOut(1, OrderLine.basket, "Ordered", today),
    OrderTryOut(2, OrderLine.basket, "In Progress", today)
  )


  def itemList(ab:ArrayBuffer[OrderLine]): Unit ={
    for(a <- ab){
      a.prod.name
    }
  }

  def main(args:Array[String]): Unit ={
    println(orderList)
  }



}