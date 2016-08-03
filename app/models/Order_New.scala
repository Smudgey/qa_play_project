package models

import reactivemongo.bson.{BSONDocument, BSONDocumentReader}
import _root_.models.Order

import scala.concurrent._
import ExecutionContext.Implicits.global


/**
  * Created by Marko on 02/08/2016.
  */
case class Order_New(orderID: Int, accountId: String, date: String, time: String, orderStatus: String, paymentMethod: String, orderLines: Array[OrderLine_New], totalPrice: Double, rating: Int) {}

object Order_New {
  implicit object OrderReader extends BSONDocumentReader[Order_New] {
    def read(doc: BSONDocument): Order_New =
      Order_New(
        doc.getAs[Int]("orderID").get,
        doc.getAs[String]("accountID").get,
        doc.getAs[String]("date").get,
        doc.getAs[String]("time").get,
        doc.getAs[String]("orderStatus").get,
        doc.getAs[String]("paymentMethod").get,
        doc.getAs[Array[OrderLine_New]]("orderLines").get,
        doc.getAs[Double]("totalPrice").get,
        doc.getAs[Int]("rating").get
      )
  }
}